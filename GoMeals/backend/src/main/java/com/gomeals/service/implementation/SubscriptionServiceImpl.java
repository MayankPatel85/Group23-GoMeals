package com.gomeals.service.implementation;

import com.gomeals.model.Customer;
import com.gomeals.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeals.model.Subscriptions;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.service.SubscriptionService;

import jakarta.transaction.Transactional;

import java.util.Collections;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public String addSubscription(Subscriptions subscription) {
		subscriptionRepository.save(subscription);
		return "Subscription added to the subscription table";
	}

	@Transactional
	public Subscriptions getSubscription(int sub_id) {
		return subscriptionRepository.findById(sub_id).orElse(null);
	}

	@Transactional
	public String updateSubscription(Subscriptions subscription) {
		Subscriptions latestSubscription = subscriptionRepository.findById(subscription.getSub_id()).orElse(null);
		latestSubscription.setActiveStatus(subscription.getActiveStatus());
		latestSubscription.setStatus(subscription.getStatus());
		latestSubscription.setMeals_remaining(subscription.getMeals_remaining());
		latestSubscription.setSub_date(subscription.getSub_date());
		subscriptionRepository.save(latestSubscription);
		return "Subscription updated successfully.";
	}

	@Transactional
	public String deleteSubscription(int sub_id) {
		subscriptionRepository.deleteById(sub_id);
		return "Subscription deleted successfully.";

	}
	@Override
	public List<Integer> getCustomers(int id){
		return subscriptionRepository.getCustomers(id);
	}

	@Override
	public List<Integer> getAllCustomerSubscriptions(int cust_id) {
		List<Integer> listOfSuppliersForCustomers = new ArrayList<>();
		subscriptionRepository.findSubscriptionsByCustomerIdAndActiveStatus(cust_id, 1)
				.forEach(subscription -> listOfSuppliersForCustomers.add(subscription.getSupplierId()));

		return listOfSuppliersForCustomers;
	}

	@Override
	public List<Subscriptions> getPendingSubscription(int supplierId) {
		List<Subscriptions> pendingSubscriptions = subscriptionRepository.findByActiveStatusAndStatusAndSupplierId(0, "Pending", supplierId);
		if(pendingSubscriptions.isEmpty()) {
			return Collections.emptyList();
		}
		pendingSubscriptions.forEach(pendingSubscription -> {
			Customer currentCustomer = customerRepository.findById(pendingSubscription.getCustomerId()).orElse(null);
			pendingSubscription.setCustomer(currentCustomer);
		});
		return pendingSubscriptions;
	}

}
