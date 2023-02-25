package com.gomeals.repository;

import org.springframework.data.repository.CrudRepository;

import com.gomeals.model.Subscriptions;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscriptions, Integer>{
    List<Subscriptions> findSubscriptionsBySupplierIdAndActiveStatus(Integer supplierId, Integer activeStatus);
    List<Subscriptions> findSubscriptionsByCustomerIdAndActiveStatus(Integer customerId, Integer activeStatus);

}
