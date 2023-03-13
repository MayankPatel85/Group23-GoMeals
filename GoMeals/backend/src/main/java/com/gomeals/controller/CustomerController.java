package com.gomeals.controller;
import com.gomeals.model.Customer;
import com.gomeals.model.Customer;
import com.gomeals.service.CustomerService;
import com.gomeals.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {


    @Autowired
    CustomerService CustomerService;
    @CrossOrigin

    @GetMapping("/get/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return CustomerService.getCustomerById(id);
    }
    @CrossOrigin

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer Customer) {
        return CustomerService.createCustomer(Customer);
    }
    @PostMapping("/login")
    public Customer loginCustomer(@RequestBody Customer customer,HttpServletResponse response) { return CustomerService.loginCustomer(customer,response);}

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return CustomerService.updateCustomer(customer);
    }
}
