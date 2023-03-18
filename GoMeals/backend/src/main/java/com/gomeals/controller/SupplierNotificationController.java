package com.gomeals.controller;

import com.gomeals.model.SupplierNotification;
import com.gomeals.service.SupplierNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/supplier-notification")
public class SupplierNotificationController {

    private final SupplierNotificationService supplierNotificationService;

    public SupplierNotificationController(SupplierNotificationService supplierNotificationService) {
        this.supplierNotificationService = supplierNotificationService;
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierNotification> createNotification(@RequestBody SupplierNotification notification) {
        return new ResponseEntity<>(supplierNotificationService.createNotification(notification), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SupplierNotification> getNotificationById(@PathVariable("id") Integer notificationId) {
        return new ResponseEntity<>(supplierNotificationService.getNotificationById(notificationId), HttpStatus.OK);
    }

    @GetMapping("/get/all-supplier/{id}")
    public ResponseEntity<List<SupplierNotification>> getAllNotificationsBySupplierId(
            @PathVariable("id") Integer supplierId) {
        return new ResponseEntity<>(supplierNotificationService.getAllNotificationsBySupplierId(supplierId),
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<SupplierNotification> updateNotification(@RequestBody SupplierNotification notification) {
        return new ResponseEntity<>(supplierNotificationService.updateNotification(notification), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") Integer notificationId) {
        supplierNotificationService.deleteNotification(notificationId);
        return ResponseEntity.status(HttpStatus.OK).body("Notification was successfully deleted.\n");
    }

}
