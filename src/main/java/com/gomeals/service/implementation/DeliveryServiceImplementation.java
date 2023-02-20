package com.gomeals.service.implementation;

import com.gomeals.model.Delivery;
import com.gomeals.repository.DeliveryRepository;
import com.gomeals.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DeliveryServiceImplementation implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;


    @Override
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDeliveryById(int id) {
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        return delivery;
    }

    @Override
    public Delivery updateDelivery(@RequestBody Delivery delivery) {
        Delivery currentDelivery = deliveryRepository.findById(delivery.getDeliveryId()).orElse(null);
        currentDelivery.setDeliveryDate(delivery.getDeliveryDate());
        currentDelivery.setDeliveryMeal(delivery.getDeliveryMeal());
        currentDelivery.setOrderStatus(delivery.getOrderStatus());
        currentDelivery.setSupId(delivery.getSupId());
        currentDelivery.setCustId(delivery.getCustId());
        deliveryRepository.save(currentDelivery);
        return currentDelivery;
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
}
