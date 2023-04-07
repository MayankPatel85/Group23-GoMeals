package com.gomeals.service.implementation;

import com.gomeals.model.Customer;
import com.gomeals.model.Subscriptions;
import com.gomeals.model.Supplier;
import com.gomeals.repository.CustomerRepository;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.repository.SupplierRepository;
import com.gomeals.service.CustomerService;
import com.gomeals.utils.UserSecurity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;


import java.util.*;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;

    public CustomerServiceImplementation() {
        super();
    }

    UserSecurity userSecurity = new UserSecurity();


    @Override
    public Customer createCustomer(Customer customer) {

        if (customerRepository.findByEmail(customer.getCust_email()) != null) {
            throw new RuntimeException("Email already exists");
        }
        customer.setCust_password(userSecurity.encryptData(customer.getCust_password()));
        return customerRepository.save(customer);
    }

    public String printCookie(@CookieValue(name = "loggedInUser", defaultValue = "") String loggedInUser) {
        return loggedInUser;
    }


    @Override
    public Customer loginCustomer(Customer customer, HttpServletResponse response) {
        Customer customerData = customerRepository.findByEmail(customer.getCust_email());
        if (customerData == null) {
            throw new RuntimeException("User not Registered");
        } else {
            String password = customerRepository.passwordMatch(customer.getCust_email());
            if (Objects.equals(userSecurity.decryptData(password), customer.getCust_password())) {
                try {
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }
                return customerData;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                customerData = null;
            }
        }
        return customerData;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {

            List<Supplier> suppliers = new ArrayList<>();
            List<Subscriptions> subscriptions = new ArrayList<>();
            subscriptionRepository.findSubscriptionsByCustomerIdAndActiveStatus(customer.getCust_id(), 1).forEach(
                    subscription -> {
                        // For each supplier that the customer is subbed to, store the supplier details
                        Optional<Supplier> supplier = supplierRepository.findById(subscription.getSupplierId());
                        suppliers.add(unwrapSupplier(supplier));
                        // Store the subscription details
                        subscriptions.add(subscription);
                    }
            );
            customer.setSuppliers(suppliers);
            customer.setSubscriptions(subscriptions);

        }
        return customer;
    }

    private static Supplier unwrapSupplier(Optional<Supplier> entity) {
        return entity.orElse(null);
    }


}
