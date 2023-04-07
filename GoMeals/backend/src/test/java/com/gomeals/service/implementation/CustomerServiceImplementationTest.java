package com.gomeals.service.implementation;

import com.gomeals.model.Customer;
import com.gomeals.repository.CustomerRepository;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.repository.SupplierRepository;
import com.gomeals.service.CustomerService;
import com.gomeals.utils.UserSecurity;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CustomerServiceImplementationTest {
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @InjectMocks
    private CustomerServiceImplementation customerServiceImplementation;
    UserSecurity userSecurity = new UserSecurity();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImplementation();
    }

    @Test
    public void createCustomerTest() {
        // given
        Customer customer = new Customer();
        customer.setCust_email("test@example.com");
        customer.setCust_password("password");

        Mockito.when(customerRepository.findByEmail(customer.getCust_email())).thenReturn(null);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        // when
        Customer createdCustomer = customerServiceImplementation.createCustomer(customer);

        // then
        assertNotNull(createdCustomer);
        assertEquals("test@example.com", createdCustomer.getCust_email());
        assertEquals("password", userSecurity.decryptData(createdCustomer.getCust_password()));

        Mockito.verify(customerRepository, Mockito.times(1)).findByEmail(customer.getCust_email());
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }
}
