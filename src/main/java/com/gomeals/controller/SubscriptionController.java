package com.gomeals.controller;

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

@RestController
@CrossOrigin
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@GetMapping("/get/{subId}")
	public Subscriptions getSubscription(@PathVariable("subId") int sub_id) {

		return subscriptionService.getSubscription(sub_id);

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

}