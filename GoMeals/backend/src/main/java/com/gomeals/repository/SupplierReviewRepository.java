package com.gomeals.repository;

import com.gomeals.model.SupplierReview;
import org.springframework.data.repository.CrudRepository;

public interface SupplierReviewRepository extends CrudRepository<SupplierReview, Integer> {

    SupplierReview findByCustomerIdAndSupplierId(int customerId, int supplierId);
}
