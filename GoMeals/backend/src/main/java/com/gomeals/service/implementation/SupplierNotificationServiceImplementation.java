package com.gomeals.service.implementation;

import com.gomeals.model.SupplierNotification;
import com.gomeals.repository.SupplierNotificationRepository;
import com.gomeals.service.SupplierNotificationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierNotificationServiceImplementation implements SupplierNotificationService {

    private final SupplierNotificationRepository supplierNotificationRepository;

    public SupplierNotificationServiceImplementation(SupplierNotificationRepository supplierNotificationRepository) {
        this.supplierNotificationRepository = supplierNotificationRepository;
    }
    /**
     * Create a new notification.
     *
     * @param supplierNotification the SupplierNotification object to be created
     * @return the created SupplierNotification object
     */

    @Override
    public SupplierNotification createNotification(SupplierNotification supplierNotification) {
        return supplierNotificationRepository.save(supplierNotification);
    }

    /**
     * Get a notification by ID.
     *
     * @param notificationId the ID of the notification to be retrieved
     * @return the retrieved SupplierNotification object, or null if not found
     */
    @Override
    public SupplierNotification getNotificationById(Integer notificationId) {
        return supplierNotificationRepository.findById(notificationId).orElse(null);
    }

    /**
     * Get all notifications by supplier ID.
     *
     * @param supplierId the ID of the supplier for which notifications are to be retrieved
     * @return a list of SupplierNotification objects for the given supplier ID
     */
    @Override
    public List<SupplierNotification> getAllNotificationsBySupplierId(Integer supplierId) {
        List<SupplierNotification> notifications = new ArrayList<>();
        supplierNotificationRepository.findAllBySupplierId(supplierId).forEach(notification ->
                notifications.add(notification)
        );
        return notifications;
    }

    /**
     * Update a notification.
     *
     * @param supplierNotification the SupplierNotification object to be updated
     * @return the updated SupplierNotification object, or null if not found
     */
    @Override
    public SupplierNotification updateNotification(SupplierNotification supplierNotification) {
        return supplierNotificationRepository.findById(supplierNotification.getNotificationId()).map(
                currentNotification -> {
                    currentNotification.setMessage(supplierNotification.getMessage());
                    currentNotification.setEventType(supplierNotification.getEventType());
                    currentNotification.setSupplierId(supplierNotification.getSupplierId());
                    return supplierNotificationRepository.save(currentNotification);
                }).orElse(null);
    }

    /**
     * Delete a notification by ID.
     *
     * @param notificationId the ID of the notification to be deleted
     * @throws NoSuchElementException if the notification with the given ID is not found
     */
    @Override
    public void deleteNotification(Integer notificationId) {
        if(supplierNotificationRepository.findById(notificationId).isEmpty()){
            throw new NoSuchElementException("Notification not found with id: "+notificationId );
        }else{
            supplierNotificationRepository.deleteById(notificationId);
        }
    }

}
