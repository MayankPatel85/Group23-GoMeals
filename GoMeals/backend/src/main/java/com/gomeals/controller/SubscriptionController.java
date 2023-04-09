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
/**
 * This controller contains the methods that is required for managing the subscription related to a customer and a supplier
 */
public class 	SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	/**
	 * This method is used to extract the subscription data using the subscription  ID
	 * @param subId
	 * @return Retrieved subscription object
	 */
	@GetMapping("/get/{subId}")
	public Subscriptions getSubscription(@PathVariable("subId") int subId) {
		return subscriptionService.getSubscription(subId);
	}

	/**
	 * This method is used to extract the suppliers ids of suppliers to which a particular customer has subscribed to
	 * @param custId
	 * @return List of supplier id's
	 */
	@GetMapping("/get/customersSubscriptions/{custId}")
	public List<Integer> getAllSuppliersForCustomer(@PathVariable int custId) {
		return subscriptionService.getAllCustomerSubscriptions(custId);
	}

	/**
	 * This method is used to extract customer ids of all the customers of a particular supplier
	 * @param id
	 * @return List of customer id's
	 */
	@GetMapping("/get/sup/{id}")
	public List<Integer> getCustomers(@PathVariable int id) {
		return subscriptionService.getCustomersIdForSupplier(id);
	}

	/**
	 * This method is used to establish a new subscription between a supplier and customer
	 * by adding an entry to the subscription table
	 * @param subscription
	 * @return String containing the status of the subscription
	 */
	@PostMapping("/add")
	public String postSubscription(@RequestBody Subscriptions subscription) {

		return subscriptionService.addSubscription(subscription);

	}

	/**
	 * This method is used to update the details of a particular subscription
	 * @param subscription subscription object
	 * @return String indicating the status of the subscription
	 */
	@PutMapping("/update")
	public String updateSubscriptions(@RequestBody Subscriptions subscription) {
		return subscriptionService.updateSubscription(subscription);
	}

	/**
	 * This method is used to delete a particular subscription using the subscription id
	 * @param sub_Id subscription id
	 * @return String indicating the status of the deletion
	 */
	@DeleteMapping("/delete/{subId}")
	public String deleteSubscriptions(@PathVariable("subId") int sub_Id) {
		return subscriptionService.deleteSubscription(sub_Id);
	}

	/**
	 * This method is used to extract the details of all the subscriptions whose details are pending
	 * Used to display the Pending subscriptions to the suppliers so they can accept or reject
	 * @param supplierId supplier id
	 * @return List of Subscription object
	 */
	@GetMapping("/get/pending/{supplierId}")
	public List<Subscriptions> getPendingSubscription(@PathVariable("supplierId") int supplierId) {
		return subscriptionService.getPendingSubscription(supplierId);
	}

}