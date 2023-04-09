package com.gomeals.service.implementation;

import com.gomeals.model.DeliveryAddons;
import com.gomeals.model.DeliveryAddonsId;
import com.gomeals.repository.DeliveryAddonsRepository;
import com.gomeals.service.DeliveryAddonsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryAddonsServiceImplementation implements DeliveryAddonsService {

    private final DeliveryAddonsRepository deliveryAddonsRepository;

    public DeliveryAddonsServiceImplementation(DeliveryAddonsRepository deliveryAddonsRepository) {
        this.deliveryAddonsRepository = deliveryAddonsRepository;
    }

    @Override
    public DeliveryAddons createDeliveryAddons(DeliveryAddons deliveryAddons) {
        return deliveryAddonsRepository.save(deliveryAddons);
    }

    @Override
    public DeliveryAddons getDeliveryAddonsById(DeliveryAddonsId deliveryAddonsId) {
        return deliveryAddonsRepository.findById(deliveryAddonsId).orElse(null);
    }

    @Override
    public List<DeliveryAddons> getDeliveryAddonsByDeliveryId(Integer deliveryId) {
        List<DeliveryAddons> deliveryAddons;
        deliveryAddons = deliveryAddonsRepository.findDeliveryAddonsByDeliveryId(deliveryId);
        return deliveryAddons;
    }

    @Override
    @Transactional
    public DeliveryAddons updateDeliveryAddon(DeliveryAddons deliveryAddons) {
        return deliveryAddonsRepository.save(deliveryAddons);
    }

    @Override
    @Transactional
    public void deleteDeliveryAddon(DeliveryAddonsId deliveryAddonId) {
        deliveryAddonsRepository.deleteById(deliveryAddonId);
    }

    @Override
    @Transactional
    public void deleteAllByDeliveryId(Integer deliveryId) {
        deliveryAddonsRepository.deleteAllByDeliveryId(deliveryId);
    }
}
