package com.gomeals.service.implementation;

import com.gomeals.model.*;
import com.gomeals.repository.*;
import com.gomeals.service.DeliveryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.gomeals.constants.DeliveryStatus.*;

@Service
public class DeliveryServiceImplementation implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SupplierRepository supplierRepository;
    private final MealChartRepository mealChartRepository;
    private final PollingRepository pollingRepository;
    private final CustomerNotificationRepository customerNotificationRepository;

    public DeliveryServiceImplementation(DeliveryRepository deliveryRepository,
                                         SubscriptionRepository subscriptionRepository,
                                         PollingRepository pollingRepository,
                                         MealChartRepository mealChartRepository,
                                         SupplierRepository supplierRepository,
                                         CustomerNotificationRepository customerNotificationRepository) {
        this.deliveryRepository = deliveryRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.pollingRepository = pollingRepository;
        this.mealChartRepository = mealChartRepository;
        this.supplierRepository = supplierRepository;
        this.customerNotificationRepository = customerNotificationRepository;
    }

    @Transactional
    @Override
    public Boolean createDelivery(Delivery delivery) {

        int supplierId = delivery.getSupId();
        int customerId = delivery.getCustId();

        LocalDateTime todayDate = LocalDateTime.now();
        LocalDateTime tomorrowDate = todayDate.plusDays(1);

        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
        if(supplier == null){
            System.out.println("That supplier does not exist.");
            return false;
        }

        // Verify that there is no active delivery for that date
        Delivery newDelivery = deliveryRepository.findBySupIdAndCustIdAndDeliveryDateAndOrderStatus(supplierId,customerId,
                tomorrowDate.toLocalDate(), IN_PROGRESS.getStatusName());
        if(newDelivery != null){
            System.out.println("A delivery has already been created for that customer and date");
            return false;
        }
        newDelivery = delivery;

        // Verify if there are enough meals remaining in the user subscription before creating a delivery
        Subscriptions subscriptions = subscriptionRepository.findSubscriptionsByCustomerIdAndSupplierIdAndActiveStatus(
                customerId, supplierId,1);
        if(subscriptions == null){
            System.out.println("User has no active subscription with the supplier.");
            return false;
        }
        if(subscriptions.getMeals_remaining() == 0){
            System.out.println("User has 0 meals remaining on the subscription.");
            return false;
        }

        // get the suppliers mealchart
        List<Object[]> mealChart = mealChartRepository.findMealChartBySupplierId(supplierId);
        if(mealChart.isEmpty()){
            System.out.println("Meal chart not found");
            return false;
        }

        String tomorrow = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(tomorrowDate);
        StringBuilder deliveryMeal = new StringBuilder();
        for(Object[] object : mealChart){

            Date date = (Date) object[7];
            String specialDate = date.toString();
            String day = (String) object[0];
            DayOfWeek tomorrowDay = tomorrowDate.getDayOfWeek();

            if(tomorrow.equals(specialDate)){ // special meal day, take most voted meal on the poll
                Polling polling = pollingRepository.findBySupIdAndStatus(supplierId,true);
                if(polling == null){
                    System.out.println("Poll not found.");
                    return false;
                }
                if(polling.getVote() == null){
                    System.out.println("Voted meal not found.");
                    return false;
                }
                deliveryMeal.append(polling.getVote());
            }else if(day.equals(tomorrowDay.toString().toLowerCase())){ // normal day, take the meal for that day
                for(int i = 1; i < 6; i++){
                    String mealChartMeal = (String) object[i];
                    if(mealChartMeal == null || mealChartMeal.isEmpty()){
                        continue;
                    }
                    deliveryMeal.append(mealChartMeal).append(",");
                }
            }
            break;
        }

        // Save the delivery
        newDelivery.setDeliveryMeal(deliveryMeal.toString());
        newDelivery.setDeliveryDate(tomorrowDate.toLocalDate());
        deliveryRepository.save(newDelivery);

        // Save notification to the user.
        CustomerNotification customerNotification = new CustomerNotification();
        customerNotification.setNotificationId(null);
        customerNotification.setMessage("A delivery was created for: "+tomorrow+" by: "+supplier.getSupName());
        customerNotification.setEventType("delivery");
        customerNotification.setCustomerId(customerId);

        customerNotificationRepository.save(customerNotification);

        return true;
    }


    @Override
    public Delivery getDeliveryById(int id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @Override
    public Delivery updateDelivery(@RequestBody Delivery delivery) {
        return deliveryRepository.findById(delivery.getDeliveryId()).map(
                currentDelivery -> {
                currentDelivery.setDeliveryDate(delivery.getDeliveryDate());
                currentDelivery.setDeliveryMeal(delivery.getDeliveryMeal());
                currentDelivery.setOrderStatus(delivery.getOrderStatus());
                currentDelivery.setSupId(delivery.getSupId());
                currentDelivery.setCustId(delivery.getCustId());
                return deliveryRepository.save(currentDelivery);
        }).orElse(null);
    }

    @Override
    public String deleteDeliveryById(int id) {
        deliveryRepository.deleteById(id);
        return "Delivery deleted!";
    }

    @Override
    public List<Delivery> getByCustId(int id) {
        return deliveryRepository.findByCustId(id);
    }

    @Transactional
    @Override
    public Delivery updateDeliveryStatus(int id, String status) {
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        if (delivery == null) {
            return null;
        }
        if(status == null || status.isEmpty()){
            return null;
        }

        String newStatus = status.toLowerCase();

        if(!CANCELLED.getStatusName().equals(newStatus) &&
                !COMPLETED.getStatusName().equals(newStatus)){
            System.out.println("Invalid status type.");
            return null;
        }

        if (!IN_PROGRESS.getStatusName().equals(delivery.getOrderStatus())) {
            System.out.println("Can't update the status of an order that it's not in progress.");
            return null;
        }

        Subscriptions subscription = subscriptionRepository.findSubscriptionsByCustomerIdAndSupplierIdAndActiveStatus(
                delivery.getCustId(), delivery.getSupId(), 1);
        if (subscription == null) {
            System.out.println("The user doesn't have an active subscription.");
            return null;
        }
        // If the user has an active subscription with that supplier
        // Modify the delivery status to either cancelled or completed
        if(CANCELLED.getStatusName().equals(newStatus)){
            delivery.setOrderStatus(CANCELLED.getStatusName());
        }else{
            // Update the remaining meals on the subscription table
            if(subscription.getMeals_remaining() == 0){
                subscription.setActiveStatus(0);
            }
            if(subscription.getMeals_remaining() > 0){
                subscription.setMeals_remaining(subscription.getMeals_remaining() - 1);
            }
            delivery.setOrderStatus(COMPLETED.getStatusName());
        }
        // Saving changes to the delivery and the new sub meal count
        deliveryRepository.save(delivery);
        subscriptionRepository.save(subscription);
        System.out.println("Order status successfully updated.");

        return delivery;
    }

}
