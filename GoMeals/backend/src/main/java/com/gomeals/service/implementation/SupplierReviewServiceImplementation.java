package com.gomeals.service.implementation;

import com.gomeals.model.SupplierReview;
import com.gomeals.repository.SupplierReviewRepository;
import com.gomeals.service.SupplierReviewService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierReviewServiceImplementation implements SupplierReviewService {
    @Autowired
    SupplierReviewRepository supplierReviewRepository;

    @Override
    public SupplierReview createSupplierReview(SupplierReview supplierReview) {return supplierReviewRepository.save(supplierReview);}
    @Override
    public List<Integer> get4upStarSupplier(){return supplierReviewRepository.find4supId();}
    @Override
    public List<Integer> get3UpStarSupplier(){return supplierReviewRepository.find3supId();}

    @Override
    public SupplierReview getSupplierReviewByCustomerIdAndSupplierId(int customerId, int supplierId) {
        return supplierReviewRepository.findByCustomerIdAndSupplierId(customerId, supplierId);
    }

}
