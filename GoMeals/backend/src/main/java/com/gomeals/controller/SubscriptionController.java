package com.gomeals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gomeals.model.Subscriptions;
import com.gomeals.service.SubscriptionService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/subscription")
public class 	SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@GetMapping("/get/{subId}")
	public Subscriptions getSubscription(@PathVariable("subId") int subId) {
		return subscriptionService.getSubscription(subId);
	}

	@GetMapping("/get/customersSubscriptions/{custId}")
	public List<Integer> getAllSuppliersForCustomer(@PathVariable int custId) {
		return subscriptionService.getAllCustomerSubscriptions(custId);
	}

	@GetMapping("/get/sup/{id}")
	public List<Integer> getCustomers(@PathVariable int id) {
		return subscriptionService.getCustomersIdForSupplier(id);
	}

	@PostMapping("/add")
	public String postSubscription(@RequestBody Subscriptions subscription) {

		return subscriptionService.addSubscription(subscription);

	}

	@PutMapping("/update")
	public String updateSubscriptions(@RequestBody Subscriptions subscription) {
		return subscriptionService.updateSubscription(subscription);
	}

	@DeleteMapping("/delete/{subId}")
	public String deleteSubscriptions(@PathVariable("subId") int sub_Id) {
		return subscriptionService.deleteSubscription(sub_Id);
	}

	@GetMapping("/get/pending/{supplierId}")
	public List<Subscriptions> getPendingSubscription(@PathVariable("supplierId") int supplierId) {
		return subscriptionService.getPendingSubscription(supplierId);
	}

}