package com.gomeals.service.implementation;


import com.gomeals.model.DeliveryAddons;
import com.gomeals.model.DeliveryAddonsId;
import com.gomeals.repository.DeliveryAddonsRepository;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeliveryAddonsServiceImplementationTest {

    @Mock
    private DeliveryAddonsRepository deliveryAddonsRepository;

    @InjectMocks
    private DeliveryAddonsServiceImplementation deliveryAddonsServiceImplementation;


    @Test
    void createDeliveryAddons() {
        DeliveryAddons deliveryAddons = new DeliveryAddons(1,1,1,5);
        when(deliveryAddonsRepository.save((deliveryAddons))).thenReturn(
                new DeliveryAddons(1,1,1,5)
                );
        DeliveryAddons deliveryAddon = deliveryAddonsServiceImplementation.createDeliveryAddons(
                deliveryAddons);

        assertEquals(5,deliveryAddon.getQuantity());
    }

    @Test
    void getDeliveryAddonsById() {
        DeliveryAddonsId deliveryAddonsId = new DeliveryAddonsId(2,2,2);
        when(deliveryAddonsRepository.findById(deliveryAddonsId)).thenReturn(
                Optional.of(new DeliveryAddons(2,2,2,20))
        );
        DeliveryAddons deliveryAddons = deliveryAddonsServiceImplementation.getDeliveryAddonsById(deliveryAddonsId);

        assertEquals(20,deliveryAddons.getQuantity());
    }

    @Test
    void getDeliveryAddonsByDeliveryId() {

        when(deliveryAddonsRepository.findDeliveryAddonsByDeliveryId(1)).thenReturn(Arrays.asList(
                new DeliveryAddons(1,1,1,5),
                new DeliveryAddons(2,2,1,20),
                new DeliveryAddons(3,3,1,15)
        ));
        List<DeliveryAddons> result = deliveryAddonsServiceImplementation.getDeliveryAddonsByDeliveryId(1);

        assertEquals(5,result.get(0).getQuantity());
        assertEquals(15,result.get(2).getQuantity());
    }

    @Test
    void updateDeliveryAddon() {

        DeliveryAddonsId deliveryAddonsId = new DeliveryAddonsId(2,2,2);
        DeliveryAddons deliveryAddons = new DeliveryAddons(1,1,1,5);
        DeliveryAddons newDeliveryAddons = new DeliveryAddons(1,1,1,20);

        when(deliveryAddonsRepository.findById(deliveryAddonsId)).thenReturn(Optional.of(deliveryAddons));
        when(deliveryAddonsRepository.save(deliveryAddons)).thenReturn(newDeliveryAddons);

        DeliveryAddons updatedDeliveryAddons = deliveryAddonsServiceImplementation.updateDeliveryAddon(deliveryAddons);

        assertEquals(20,updatedDeliveryAddons.getQuantity());
    }

    @Test
    void deleteDeliveryAddon() {
        DeliveryAddonsId deliveryAddonsId = new DeliveryAddonsId(2,2,2);
        DeliveryAddons deliveryAddons = new DeliveryAddons(2,2,2,5);

        when(deliveryAddonsRepository.findById(deliveryAddonsId)).thenReturn(Optional.of(deliveryAddons));
        deliveryAddonsServiceImplementation.deleteDeliveryAddon(deliveryAddonsId);
        verify(deliveryAddonsRepository).deleteById(deliveryAddonsId);
    }

    @Test
    void deleteAllByDeliveryId() {
        when(deliveryAddonsRepository.findDeliveryAddonsByDeliveryId(1)).thenReturn(Arrays.asList(
                new DeliveryAddons(1,1,1,5),
                new DeliveryAddons(2,2,1,20),
                new DeliveryAddons(3,3,1,15)
        ));
        deliveryAddonsServiceImplementation.deleteAllByDeliveryId(1);
        // Verify that the delete method from the repository was executed
        verify(deliveryAddonsRepository).deleteAllByDeliveryId(1);
    }
}