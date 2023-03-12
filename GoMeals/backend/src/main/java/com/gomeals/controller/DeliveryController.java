package com.gomeals.controller;

import com.gomeals.model.Delivery;
import com.gomeals.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "http://localhost:3000")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/get/{id}")
    public Delivery getDeliveryById(@PathVariable("id") int id) {
        return deliveryService.getDeliveryById(id);
    }

    @GetMapping("/get/customer/{id}")
    public List<Delivery> getByCustomerId(@PathVariable int id) {
        return deliveryService.getByCustId(id);
    }

    @PostMapping("/create")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("/update")
    public Delivery updateDelivery(@RequestBody Delivery delivery) {
        return deliveryService.updateDelivery(delivery);
    }

    @PutMapping("/update-status/")
    public ResponseEntity<Delivery> updateDeliveryStatus(@RequestParam int deliveryId, String orderStatus) {
        Delivery deliveryToUpdate = deliveryService.updateDeliveryStatus(deliveryId, orderStatus);
        if (deliveryToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(deliveryToUpdate, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDeliveryById(@PathVariable int id) {
        return deliveryService.deleteDeliveryById(id);
    }

}
