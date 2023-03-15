package com.gomeals.service.implementation;

import com.gomeals.constants.DeliveryStatus;
import com.gomeals.model.Delivery;
import com.gomeals.model.Subscriptions;
import com.gomeals.repository.DeliveryRepository;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.service.DeliveryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.gomeals.constants.DeliveryStatus.*;

@Service
public class DeliveryServiceImplementation implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    private final SubscriptionRepository subscriptionRepository;

    public DeliveryServiceImplementation(DeliveryRepository deliveryRepository,
                                         SubscriptionRepository subscriptionRepository) {
        this.deliveryRepository = deliveryRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
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
            subscription.setMeals_remaining(subscription.getMeals_remaining() - 1);
            delivery.setOrderStatus(COMPLETED.getStatusName());
        }
        // Saving changes to the delivery and the new sub meal count
        deliveryRepository.save(delivery);
        subscriptionRepository.save(subscription);
        System.out.println("Order status successfully updated.");

        return delivery;
    }

}
