package com.gomeals.service.implementation;

import com.gomeals.model.Customer;
import com.gomeals.model.CustomerNotification;
import com.gomeals.model.Subscriptions;
import com.gomeals.repository.CustomerNotificationRepository;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.repository.SupplierRepository;
import com.gomeals.service.CustomerNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerNotificationServiceImplementation implements CustomerNotificationService {

    private final CustomerNotificationRepository customerNotificationRepository;
    private final SubscriptionRepository subscriptionRepository;
    public CustomerNotificationServiceImplementation(CustomerNotificationRepository customerNotificationRepository,
                                                     SubscriptionRepository subscriptionRepository) {
        this.customerNotificationRepository = customerNotificationRepository;
        this.subscriptionRepository = subscriptionRepository;

    }

    @Override
    public CustomerNotification createNotification(CustomerNotification customerNotification) {
        return customerNotificationRepository.save(customerNotification);
    }

    @Override
    public CustomerNotification getNotificationById(Integer notificationId) {
        return customerNotificationRepository.findById(notificationId).orElse(null);
    }

    @Override
    public List<CustomerNotification> getAllNotificationsByCustomerId(Integer customerId) {
        List<CustomerNotification> notifications = new ArrayList<>();
        customerNotificationRepository.findAllByCustomerId(customerId)
                .forEach(notification -> notifications.add(notification));
        return notifications;
    }

    @Override
    public CustomerNotification updateNotification(CustomerNotification customerNotification) {
        return customerNotificationRepository.findById(customerNotification.getNotificationId()).map(
                currentNotification -> {
                    currentNotification.setMessage(customerNotification.getMessage());
                    currentNotification.setEventType(customerNotification.getEventType());
                    currentNotification.setCustomerId(customerNotification.getCustomerId());
                    return customerNotificationRepository.save(currentNotification);
                }).orElse(null);
    }

    @Override
    public void deleteNotification(Integer notificationId) {
        if (customerNotificationRepository.findById(notificationId).isEmpty()) {
            throw new NoSuchElementException("Notification not found with id: " + notificationId);
        } else {
            customerNotificationRepository.deleteById(notificationId);
        }
    }

    public Boolean notifyAllSupplierCustomers(String message, String type, int supplierId){

        List<Subscriptions> supplierSubscriptions = subscriptionRepository
                .findSubscriptionsBySupplierIdAndActiveStatus(supplierId,1);
        if(supplierSubscriptions.isEmpty()){
            System.out.println("This supplier doesn't have any subscribed customers.");
            return false;
        }

        for (Subscriptions subscription : supplierSubscriptions) {
            CustomerNotification customerNotification = new CustomerNotification();
            customerNotification.setMessage(message);
            customerNotification.setEventType(type);
            customerNotification.setCustomerId(subscription.getCustomerId());

            System.out.println(subscription.getCustomerId());

            customerNotificationRepository.save(customerNotification);
        }
        System.out.println("Successfully saved notifications for all the customers.");

        return true;

    }
}
