package com.gomeals.controller;
import com.gomeals.model.Customer;
import com.gomeals.model.Supplier;
import com.gomeals.service.SupplierService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@CrossOrigin(origins = "http://localhost:3000")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @CrossOrigin
    @PostMapping("/create")
    public Supplier registerSupplier(@RequestBody Supplier supplier) {
        return supplierService.registerSupplier(supplier);
    }

    @GetMapping("/get/{id}")
    public Supplier getSupplierDetails(@PathVariable int id) {
        return supplierService.getSupplierDetails(id);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public Supplier updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable int id) {
        return supplierService.deleteSupplier(id);
    }
    @PostMapping("/login")
    public Supplier loginSupplier(@RequestBody Supplier supplier, HttpServletResponse response) { return supplierService.loginSupplier(supplier,response);}
}


