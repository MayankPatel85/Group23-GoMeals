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
	public Subscriptions getSubscription(int subId) {
		return subscriptionRepository.findById(subId).orElse(null);

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
	public String deleteSubscription(int subId) {
		subscriptionRepository.deleteById(subId);

		return "Subscription deleted successfully.";

	}

	@Override
	public List<Integer> getCustomersIdForSupplier(int supId) {
		return subscriptionRepository.getCustomersIdForSupplier(supId);

	}

	@Override
	public List<Integer> getAllCustomerSubscriptions(int custId) {
		List<Integer> listOfSuppliersForCustomers = new ArrayList<>();
		List<Subscriptions> litstOfSubscriptions;
		litstOfSubscriptions = subscriptionRepository.findSubscriptionsByCustomerIdAndActiveStatus(custId, 1);

		litstOfSubscriptions.forEach(subscription -> listOfSuppliersForCustomers.add(subscription.getSupplierId()));

		return listOfSuppliersForCustomers;
	}

	@Override
	public List<Subscriptions> getPendingSubscription(int supplierId) {
		List<Subscriptions> pendingSubscriptions;
		pendingSubscriptions = subscriptionRepository.findByActiveStatusAndStatusAndSupplierId(0,
				"Pending", supplierId);
		if (pendingSubscriptions.isEmpty()) {
			return Collections.emptyList();
		}
		pendingSubscriptions.forEach(pendingSubscription -> {
			Customer currentCustomer;

			currentCustomer = customerRepository.findById(pendingSubscription.getCustomerId()).orElse(null);
			pendingSubscription.setCustomer(currentCustomer);
		});
		return pendingSubscriptions;
	}

}
