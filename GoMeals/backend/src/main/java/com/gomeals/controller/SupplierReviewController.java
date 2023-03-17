package com.gomeals.controller;
import com.gomeals.model.Delivery;
import com.gomeals.model.SupplierReview;
import com.gomeals.service.SupplierReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplierReview")
@CrossOrigin(origins = "http://localhost:3000")
public class SupplierReviewController {
    @Autowired
    SupplierReviewService supplierReviewService;

    @PostMapping("/create")
    public SupplierReview createSupplierReview(@RequestBody SupplierReview supplierReview) {
        return supplierReviewService.createSupplierReview(supplierReview);
    }
    @GetMapping("/get/5s")
    public List<Integer> get5StarSupplier(){
        return  supplierReviewService.get5StarSupplier();
    }
    @GetMapping("/get/3us")
    public List<Integer> get3UpStarSupplier(){
        return  supplierReviewService.get3UpStarSupplier();
    }
}
