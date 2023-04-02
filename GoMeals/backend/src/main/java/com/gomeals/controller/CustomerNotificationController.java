package com.gomeals.controller;

import com.gomeals.model.CustomerNotification;
import com.gomeals.service.CustomerNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-notification")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerNotificationController {

    private final CustomerNotificationService customerNotificationService;

    public CustomerNotificationController(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }
    @CrossOrigin

    @PostMapping("/create")
    public ResponseEntity<CustomerNotification> createNotification(@RequestBody CustomerNotification notification) {
        return new ResponseEntity<>(customerNotificationService.createNotification(notification), HttpStatus.CREATED);
    }
    @CrossOrigin

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerNotification> getNotificationById(@PathVariable("id") Integer notificationId) {
        return new ResponseEntity<>(customerNotificationService.getNotificationById(notificationId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/all-customer/{id}")
    public ResponseEntity<List<CustomerNotification>> getAllNotificationsByCustomerId(@PathVariable("id") Integer customerId) {
        return new ResponseEntity<>(customerNotificationService.getAllNotificationsByCustomerId(customerId), HttpStatus.OK);
    }
    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity<CustomerNotification> updateNotification(@RequestBody CustomerNotification notification) {
        return new ResponseEntity<>(customerNotificationService.updateNotification(notification), HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") Integer notificationId) {
        customerNotificationService.deleteNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK).body("Notification was successfully deleted.\n");
    }
}
