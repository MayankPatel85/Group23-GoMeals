package com.gomeals.service;

import com.gomeals.model.CustomerNotification;

import java.util.List;

public interface CustomerNotificationService {
    // CREATE notification
    CustomerNotification createNotification(CustomerNotification customerNotification);
    // RETRIEVE notification
    CustomerNotification getNotificationById(Integer notificationId);
    // RETRIEVE all Notifications
    List<CustomerNotification> getAllNotificationsByCustomerId(Integer customerId);
    // UPDATE notification
    CustomerNotification updateNotification(CustomerNotification customerNotification);
    // DELETE notification
    void deleteNotification(Integer notificationId);
}
