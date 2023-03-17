package com.gomeals.service;
import com.gomeals.model.SupplierReview;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface SupplierReviewService {

    SupplierReview createSupplierReview(SupplierReview supplierReview);
    List<Integer>  get5StarSupplier();
    List<Integer>  get3UpStarSupplier();


}
