package com.gomeals.service;

import com.gomeals.model.Subscriptions;

public interface SubscriptionService {
	
	public String addSubscription(Subscriptions subscription);
	
	public Subscriptions getSubscription(int sub_id);
	
	public String updateSubscription(Subscriptions subscription);
	
	public String deleteSubscription(int sub_id);
	
}
