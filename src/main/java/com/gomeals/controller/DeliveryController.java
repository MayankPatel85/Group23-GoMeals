package com.gomeals.controller;

import com.gomeals.model.Delivery;
import com.gomeals.repository.DeliveryRepository;
import com.gomeals.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

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

    @DeleteMapping("/delete/{id}")
    public String deleteDeliveryById(@PathVariable int id) {
        return deliveryService.deleteDeliveryById(id);
    }

}
