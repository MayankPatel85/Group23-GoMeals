package com.gomeals.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gomeals.model.Subscriptions;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscriptions, Integer> {
    List<Subscriptions> findSubscriptionsBySupplierIdAndActiveStatus(Integer supplierId, Integer activeStatus);

    List<Subscriptions> findSubscriptionsByCustomerIdAndActiveStatus(Integer customerId, Integer activeStatus);

    Subscriptions findSubscriptionsByCustomerIdAndSupplierIdAndActiveStatus(Integer customerId, Integer supplierId,
            Integer activeStatus);

    @Query("SELECT s.customerId FROM Subscriptions s WHERE s.supplierId=:id")
    List<Integer> getCustomers(@Param("id") int id);

}
