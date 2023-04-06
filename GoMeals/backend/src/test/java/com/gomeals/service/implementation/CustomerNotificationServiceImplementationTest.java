package com.gomeals.service.implementation;

import com.gomeals.model.CustomerNotification;
import com.gomeals.repository.CustomerNotificationRepository;
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
class CustomerNotificationServiceImplementationTest {

    @Mock
    private CustomerNotificationRepository customerNotificationRepository;

    @InjectMocks
    private CustomerNotificationServiceImplementation customerNotificationServiceImplementation;

    @Test
    void createNotification() {
        CustomerNotification customerNotification = new CustomerNotification(1,"a delivery was created",
                "delivery",5);
        when(customerNotificationRepository.save((customerNotification))).thenReturn(
                new CustomerNotification(1,"a delivery was created",
                        "delivery",5)
        );
        CustomerNotification newCustomerNotification = customerNotificationServiceImplementation.createNotification(
                customerNotification);

        assertEquals("delivery",newCustomerNotification.getEventType());
    }

    @Test
    void getNotificationById() {
        when(customerNotificationRepository.findById(1)).thenReturn(
                Optional.of(new CustomerNotification(1,"a delivery was created",
                        "delivery",5))
        );
        CustomerNotification customerNotification = customerNotificationServiceImplementation
                .getNotificationById(1);

        assertEquals(5,customerNotification.getCustomerId());
    }

    @Test
    void getAllNotificationsByCustomerId() {
        when(customerNotificationRepository.findAllByCustomerId(5)).thenReturn(Arrays.asList(
                new CustomerNotification(1,"a delivery was created",
                        "delivery",5),
                new CustomerNotification(2,"Eren has approved your subscription request.",
                        "subscription",5),
                new CustomerNotification(3,"Jane Doe has provided feedback to your complain",
                        "complain solved",5)
        ));
        List<CustomerNotification> result = customerNotificationServiceImplementation.getAllNotificationsByCustomerId(5);

        assertEquals("delivery",result.get(0).getEventType());
        assertEquals("Eren has approved your subscription request.",result.get(1).getMessage());
    }

    @Test
    void updateNotification() {
        CustomerNotification customerNotification = new CustomerNotification(1,
                "Jane Doe has provided feedback to your complain", "subscription",5);
        CustomerNotification newCustomerNotification = new CustomerNotification(1,
                "John Doe has provided feedback to your complain", "subscription",5);

        when(customerNotificationRepository.findById(1)).thenReturn(Optional.of(customerNotification));
        when(customerNotificationRepository.save(customerNotification)).thenReturn(newCustomerNotification);

        CustomerNotification updatedCustomerNotification = customerNotificationServiceImplementation
                .updateNotification(customerNotification);

        assertEquals("John Doe has provided feedback to your complain",updatedCustomerNotification.getMessage());
    }

    @Test
    void deleteNotification() {
        CustomerNotification customerNotification = new CustomerNotification(1,"Eren has approved your subscription request.",
                "subscription",5);

        when(customerNotificationRepository.findById(1)).thenReturn(Optional.of(customerNotification));
        customerNotificationServiceImplementation.deleteNotification(1);
        verify(customerNotificationRepository).deleteById(1);
    }
}