package com.gomeals.controller;

import com.gomeals.model.DeliveryAddons;
import com.gomeals.model.DeliveryAddonsId;
import com.gomeals.service.DeliveryAddonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryAddons")
@CrossOrigin
public class DeliveryAddonsController {
    @Autowired
    DeliveryAddonsService deliveryAddonsService;

    @PostMapping("/create")
    public ResponseEntity<DeliveryAddons> createDeliveryAddons(@RequestBody DeliveryAddons deliveryAddons) {
        return new ResponseEntity<>(deliveryAddonsService.createDeliveryAddons(deliveryAddons), HttpStatus.CREATED);
    }

    @PostMapping("/getDeliveryAddons")
    public ResponseEntity<DeliveryAddons> getDeliveryAddonsById(@RequestBody DeliveryAddonsId deliveryAddonsId) {
        return new ResponseEntity<>(deliveryAddonsService.getDeliveryAddonsById(deliveryAddonsId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get/{id}")
    public ResponseEntity<List<DeliveryAddons>> getDeliveryAddonsByDeliveryId(@PathVariable("id") Integer deliveryId) {
        return new ResponseEntity<>(deliveryAddonsService.getDeliveryAddonsByDeliveryId(deliveryId), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DeliveryAddons> updateDeliveryAddon(@RequestBody DeliveryAddons deliveryAddon) {
        return new ResponseEntity<>(deliveryAddonsService.updateDeliveryAddon(deliveryAddon), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDeliveryAddon(@RequestBody DeliveryAddonsId deliveryAddonId) {
        deliveryAddonsService.deleteDeliveryAddon(deliveryAddonId);
        return ResponseEntity.status(HttpStatus.OK).body("Delivery addon was successfully deleted.\n");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAllByDeliveryId(@PathVariable("id") Integer deliveryId) {
        deliveryAddonsService.deleteAllByDeliveryId(deliveryId);
        return ResponseEntity.status(HttpStatus.OK).body("All delivery addons were successfully deleted.\n");
    }

}
