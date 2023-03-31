package com.gomeals.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomeals.model.Subscriptions;
import com.gomeals.repository.SubscriptionRepository;
import com.gomeals.service.SubscriptionService;

import jakarta.transaction.Transactional;

import java.util.List;

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
	public Subscriptions getSubscription(int sub_id) {
		return subscriptioRepository.findById(sub_id).orElse(null);
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
	public String deleteSubscription(int sub_id) {
		subscriptioRepository.deleteById(sub_id);
		return "Subscription deleted successfully.";

	}
	@Override
	public List<Integer> getCustomers(int id){
		return subscriptioRepository.getCustomers(id);
	}

	@Override
	public List<Integer> getAllCustomerSubscriptions(int cust_id) {
		List<Integer> listOfSuppliersForCustomers = new ArrayList<>();
		subscriptioRepository.findSubscriptionsByCustomerIdAndActiveStatus(cust_id, 1)
				.forEach(subscription -> listOfSuppliersForCustomers.add(subscription.getSupplierId()));

		return listOfSuppliersForCustomers;
	}

}
