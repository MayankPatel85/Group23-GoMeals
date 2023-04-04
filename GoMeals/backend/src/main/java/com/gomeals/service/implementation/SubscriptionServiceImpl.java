package com.gomeals.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeals.model.Subscriptions;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.service.SubscriptionService;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptioRepository;

	@Transactional
	public String addSubscription(Subscriptions subscription) {
		subscriptioRepository.save(subscription);
		return "Subscription added to the subscription table";
	}

	@Transactional
	public Subscriptions getSubscription(int subId) {
		return subscriptioRepository.findById(subId).orElse(null);
	}

	@Transactional
	public String updateSubscription(Subscriptions subscription) {
		Subscriptions latestSubscription = subscriptioRepository.findById(subscription.getSub_id()).orElse(null);
		latestSubscription.setMeals_remaining(subscription.getMeals_remaining());
		latestSubscription.setSub_date(subscription.getSub_date());
		subscriptioRepository.save(latestSubscription);
		return "Subscription updated successfully.";
	}

	@Transactional
	public String deleteSubscription(int subId) {
		subscriptioRepository.deleteById(subId);
		return "Subscription deleted successfully.";

	}

	@Override
	public List<Integer> getCustomersIdForSupplier(int supId) {
		return subscriptioRepository.getCustomersIdForSupplier(supId);
	}

	@Override
	public List<Integer> getAllCustomerSubscriptions(int custId) {
		List<Integer> listOfSuppliersForCustomers = new ArrayList<>();
		subscriptioRepository.findSubscriptionsByCustomerIdAndActiveStatus(custId, 1)
				.forEach(subscription -> listOfSuppliersForCustomers.add(subscription.getSupplierId()));

		return listOfSuppliersForCustomers;
	}

}
