package com.gomeals.service.implementation;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gomeals.model.SupplierReview;
import com.gomeals.repository.SupplierReviewRepository;
import com.gomeals.service.SupplierReviewService;
public class SupplierReviewServiceImplTest {

    @Mock
    private SupplierReviewRepository supplierReviewRepository;

    @InjectMocks
    private SupplierReviewServiceImplementation supplierReviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createSupplierReviewTest() {
        SupplierReview supplierReview = new SupplierReview();
        supplierReview.setCustomerId(1);
        supplierReview.setSupplierId(1);
        supplierReview.setSupplier_rating(5);
        supplierReview.setComment("Great food and service!");

        when(supplierReviewRepository.save(supplierReview)).thenReturn(supplierReview);

        SupplierReview createdReview = supplierReviewService.createSupplierReview(supplierReview);

        Assertions.assertEquals(createdReview.getCustomerId(), supplierReview.getCustomerId());
        Assertions.assertEquals(createdReview.getSupplierId(), supplierReview.getSupplierId());
        Assertions.assertEquals(createdReview.getSupplier_rating(), supplierReview.getSupplier_rating());
        Assertions.assertEquals(createdReview.getSupplier_reviewcol(), supplierReview.getSupplier_reviewcol());

        verify(supplierReviewRepository, times(1)).save(supplierReview);
    }

}
