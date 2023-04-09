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
        private CustomerNotificationRepository custNotifyRepo;

        @InjectMocks
        private CustomerNotificationServiceImplementation custNotifyServiceImpl;

        @Test
        void createNotification() {
                CustomerNotification customerNotification = new CustomerNotification(1, "a delivery was created",
                                "delivery", 5);
                CustomerNotification savedCustomerNotify = new CustomerNotification(1, "a delivery was created",
                                "delivery", 5);
                when(custNotifyRepo.save((customerNotification))).thenReturn(savedCustomerNotify);
                CustomerNotification newCustomerNotification = custNotifyServiceImpl
                                .createNotification(
                                                customerNotification);

                assertEquals("delivery", newCustomerNotification.getEventType());
        }

        @Test
        void getNotificationById() {

                CustomerNotification searchCustNotify = new CustomerNotification(1, "a delivery was created",
                                "delivery", 5);
                when(custNotifyRepo.findById(1)).thenReturn(
                                Optional.of(searchCustNotify));
                CustomerNotification customerNotification = custNotifyServiceImpl
                                .getNotificationById(1);

                assertEquals(5, customerNotification.getCustomerId());
        }

        @Test
        void getAllNotificationsByCustomerId() {

                CustomerNotification notification1;
                notification1 = new CustomerNotification(1, "a delivery was created",
                                "delivery", 5);
                CustomerNotification notification2;
                notification2 = new CustomerNotification(2,
                                "Eren has approved your subscription request.",
                                "subscription", 5);
                CustomerNotification notification3;
                notification3 = new CustomerNotification(3,
                                "Jane Doe has provided feedback to your complain",
                                "complain solved", 5);
                when(custNotifyRepo.findAllByCustomerId(5)).thenReturn(Arrays.asList(
                                notification1,
                                notification2,
                                notification3));
                List<CustomerNotification> result = custNotifyServiceImpl.getAllNotificationsByCustomerId(5);

                assertEquals("delivery", result.get(0).getEventType());
                assertEquals("Eren has approved your subscription request.", result.get(1).getMessage());
        }

        @Test
        void updateNotification() {
                CustomerNotification customerNotification;
                customerNotification = new CustomerNotification(1,
                                "Jane Doe has provided feedback to your complain", "subscription", 5);
                CustomerNotification newCustomerNotification;
                newCustomerNotification = new CustomerNotification(1,
                                "John Doe has provided feedback to your complain", "subscription", 5);

                when(custNotifyRepo.findById(1)).thenReturn(Optional.of(customerNotification));
                when(custNotifyRepo.save(customerNotification)).thenReturn(newCustomerNotification);

                CustomerNotification updatedCustomerNotification = custNotifyServiceImpl
                                .updateNotification(customerNotification);

                assertEquals("John Doe has provided feedback to your complain",
                                updatedCustomerNotification.getMessage());
        }

        @Test
        void deleteNotification() {
                CustomerNotification customerNotification;
                customerNotification = new CustomerNotification(1,
                                "Eren has approved your subscription request.",
                                "subscription", 5);

                when(custNotifyRepo.findById(1)).thenReturn(Optional.of(customerNotification));
                custNotifyServiceImpl.deleteNotification(1);
                verify(custNotifyRepo).deleteById(1);
        }
}