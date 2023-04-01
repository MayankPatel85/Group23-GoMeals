package com.gomeals.service.implementation;

import com.gomeals.model.SupplierReview;
import com.gomeals.repository.SupplierReviewRepository;
import com.gomeals.service.SupplierReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierReviewServiceImplementation implements SupplierReviewService {
    @Autowired
    SupplierReviewRepository supplierReviewRepository;

    @Override
    public SupplierReview createSupplierReview(SupplierReview supplierReview) {return supplierReviewRepository.save(supplierReview);}

    @Override
    public SupplierReview getSupplierReviewByCustomerIdAndSupplierId(int customerId, int supplierId) {
        return supplierReviewRepository.findByCustomerIdAndSupplierId(customerId, supplierId);
    }

}
