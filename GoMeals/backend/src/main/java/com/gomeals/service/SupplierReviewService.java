package com.gomeals.service;
import com.gomeals.model.SupplierReview;
public interface SupplierReviewService {

    SupplierReview createSupplierReview(SupplierReview supplierReview);

    SupplierReview getSupplierReviewByCustomerIdAndSupplierId(int customerId, int supplierId);


}
