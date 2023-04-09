package com.gomeals.controller;

import com.gomeals.model.Complain;
import com.gomeals.service.ComplainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complain")
@CrossOrigin
public class ComplainController {

    private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/create")
    public ResponseEntity<Complain> createComplain(@RequestBody Complain complain) {
        return new ResponseEntity<>(complainService.createComplain(complain), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Complain> getComplainById(@PathVariable("id") Integer complainId) {
        return new ResponseEntity<>(complainService.getComplainById(complainId), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Complain>> getAllComplains() {
        return new ResponseEntity<>(complainService.getAllComplains(), HttpStatus.OK);
    }

    @GetMapping("/get/all-customer/{id}")
    public ResponseEntity<List<Complain>> getAllCustomerComplains(@PathVariable("id") Integer customerId) {
        return new ResponseEntity<>(complainService.getComplainsByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/get/all-supplier/{id}")
    public ResponseEntity<List<Complain>> getAllSupplierComplains(@PathVariable("id") Integer supplierId) {
        return new ResponseEntity<>(complainService.getComplainsBySupplierId(supplierId), HttpStatus.OK);
    }

    @GetMapping("/all-supplier-customer")
    public ResponseEntity<List<Complain>> getAllComplainsByCustomerAndSupplier(@RequestParam Integer customerId,
            Integer supplierId) {
        return new ResponseEntity<>(complainService.getComplainsByCustomerIdAndSupplierId(customerId, supplierId),
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Complain> updateComplain(@RequestBody Complain complain) {
        return new ResponseEntity<>(complainService.updateComplain(complain), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComplain(@PathVariable("id") Integer complainId) {
        complainService.deleteComplain(complainId);
        return ResponseEntity.status(HttpStatus.OK).body("Complain was successfully deleted.\n");
    }
}
