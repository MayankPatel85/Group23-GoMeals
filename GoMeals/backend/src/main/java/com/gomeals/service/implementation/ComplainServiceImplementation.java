package com.gomeals.service.implementation;

import com.gomeals.model.Complain;
import com.gomeals.repository.ComplainRepository;
import com.gomeals.service.ComplainService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplainServiceImplementation implements ComplainService {
    private final ComplainRepository complainRepository;

    public ComplainServiceImplementation(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }

    @Override
    public Complain createComplain(Complain complain) {
        return complainRepository.save(complain);
    }

    @Override
    public Complain getComplainById(Integer complainId) {
        Complain complain = complainRepository.findById(complainId).orElse(null);
        return complain;
    }

    @Override
    public List<Complain> getAllComplains() {
        List<Complain> complains = new ArrayList<>();
        complainRepository.findAll().forEach(complain -> complains.add(complain));
        return complains;
    }

    @Override
    public List<Complain> getComplainsByCustomerId(Integer customerId) {
        List<Complain> complains = new ArrayList<>();
        complainRepository.findComplainsByCustomerId(customerId).forEach(complain -> complains.add(complain));
        return complains;
    }

    @Override
    public List<Complain> getComplainsBySupplierId(Integer supplierId) {
        List<Complain> complains = new ArrayList<>();
        complainRepository.findComplainsBySupplierId(supplierId).forEach(complain -> complains.add(complain));
        return complains;
    }

    @Override
    public List<Complain> getComplainsByCustomerIdAndSupplierId(Integer customerId, Integer supplierId) {
        List<Complain> complains;
        complains = complainRepository.findComplainsByCustomerIdAndSupplierId(customerId, supplierId);
        return complains;
    }

    @Override
    public Complain updateComplain(Complain complain) {
        return complainRepository.save(complain);
    }

    @Transactional
    @Override
    public void deleteComplain(Integer complainId) { // throws NoSuchElementException {
        if (complainRepository.findById(complainId).isPresent()) {
            complainRepository.deleteById(complainId);
        } else {

            // throw new NoSuchElementException("Complain not found with id: "+complainId );
        }
    }
}
