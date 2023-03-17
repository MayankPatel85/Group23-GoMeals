package com.gomeals.repository;

import com.gomeals.model.SupplierReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierReviewRepository extends CrudRepository<SupplierReview, Integer> {
    @Query("SELECT s.supplierId FROM SupplierReview s WHERE s.supplier_rating = 5")
    List<Integer> find5supId();

    @Query("SELECT s.supplierId FROM SupplierReview s WHERE s.supplier_rating >= 3")
    List<Integer> find3supId();
}
