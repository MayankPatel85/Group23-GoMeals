package com.gomeals.repository;

import com.gomeals.model.SupplierReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierReviewRepository extends CrudRepository<SupplierReview, Integer> {

    @Query("SELECT s.supplierId FROM SupplierReview s WHERE s.supplierId IN (SELECT sr.supplierId FROM SupplierReview sr GROUP BY sr.supplierId HAVING AVG(sr.supplier_rating) > 4)")
    List<Integer> find4supId();

    @Query("SELECT s.supplierId FROM SupplierReview s WHERE s.supplierId IN (SELECT sr.supplierId FROM SupplierReview sr GROUP BY sr.supplierId HAVING AVG(sr.supplier_rating) > 3)")
    List<Integer> find3supId();

    @Query("SELECT COALESCE(avg(s.supplier_rating),0.0) FROM SupplierReview s WHERE s.supplierId = :supplierId")
    Double findSupplierAverage(int supplierId);
}
