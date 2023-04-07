package com.gomeals.service.implementation;

import com.gomeals.model.SupplierNotification;
import com.gomeals.repository.SupplierNotificationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SupplierNotificationServiceImplementationTest {

    @Mock
    private SupplierNotificationRepository supplierNotificationRepository;

    @InjectMocks
    private SupplierNotificationServiceImplementation supplierNotificationServiceImplementation;

    @Test
    void createNotification() {
        SupplierNotification supplierNotification = new SupplierNotification(1,"new user subscribed.",
                "subscription",5);
        when(supplierNotificationRepository.save((supplierNotification))).thenReturn(
                new SupplierNotification(1,"new user subscribed.",
                        "subscription",5)
        );
        SupplierNotification newSupplierNotification = supplierNotificationServiceImplementation.createNotification(
                supplierNotification);

        assertEquals("subscription",newSupplierNotification.getEventType());
    }

    @Test
    void getNotificationById() {
        when(supplierNotificationRepository.findById(1)).thenReturn(
                Optional.of(new SupplierNotification(1,"new user subscribed.",
                        "subscription",5))
        );
        SupplierNotification supplierNotification = supplierNotificationServiceImplementation
                .getNotificationById(1);

        assertEquals(5,supplierNotification.getSupplierId());
    }

    @Test
    void getAllNotificationsBySupplierId() {
        when(supplierNotificationRepository.findAllBySupplierId(5)).thenReturn(Arrays.asList(
                new SupplierNotification(1,"new user subscribed.",
                        "subscription",5),
                new SupplierNotification(2,"new complain raised on the portal.",
                        "complain",5),
                new SupplierNotification(3,"new review added.",
                        "review",5)
        ));
        List<SupplierNotification> result = supplierNotificationServiceImplementation.getAllNotificationsBySupplierId(5);

        assertEquals("review",result.get(2).getEventType());
        assertEquals("new user subscribed.",result.get(0).getMessage());
    }

    @Test
    void updateNotification() {

        SupplierNotification supplierNotification = new SupplierNotification(1,
                "new user subscribed.", "subscription",5);
        SupplierNotification newSupplierNotification = new SupplierNotification(1,
                "new user requested subscription", "subscription",5);

        when(supplierNotificationRepository.findById(1)).thenReturn(Optional.of(supplierNotification));
        when(supplierNotificationRepository.save(supplierNotification)).thenReturn(newSupplierNotification);

        SupplierNotification updatedSupplierNotification = supplierNotificationServiceImplementation
                .updateNotification(supplierNotification);

        assertEquals("new user requested subscription",updatedSupplierNotification.getMessage());
    }

    @Test
    void deleteNotification() {
        SupplierNotification supplierNotification = new SupplierNotification(1,
                "new user subscribed.", "subscription",5);

        when(supplierNotificationRepository.findById(1)).thenReturn(Optional.of(supplierNotification));
        supplierNotificationServiceImplementation.deleteNotification(1);
        verify(supplierNotificationRepository).deleteById(1);
    }
}