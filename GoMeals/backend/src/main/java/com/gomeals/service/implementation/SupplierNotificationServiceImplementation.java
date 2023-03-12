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

    @Override
    public SupplierNotification createNotification(SupplierNotification supplierNotification) {
        return supplierNotificationRepository.save(supplierNotification);
    }

    @Override
    public SupplierNotification getNotificationById(Integer notificationId) {
        return supplierNotificationRepository.findById(notificationId).orElse(null);
    }

    @Override
    public List<SupplierNotification> getAllNotificationsBySupplierId(Integer supplierId) {
        List<SupplierNotification> notifications = new ArrayList<>();
        supplierNotificationRepository.findAllBySupplierId(supplierId).forEach(notification ->
                notifications.add(notification)
        );
        return notifications;
    }

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

    @Override
    public void deleteNotification(Integer notificationId) {
        if(supplierNotificationRepository.findById(notificationId).isEmpty()){
            throw new NoSuchElementException("Notification not found with id: "+notificationId );
        }else{
            supplierNotificationRepository.deleteById(notificationId);
        }
    }

}
