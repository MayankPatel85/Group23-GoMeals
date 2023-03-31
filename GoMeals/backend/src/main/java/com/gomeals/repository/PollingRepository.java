package com.gomeals.repository;

import com.gomeals.model.Polling;
import org.springframework.data.repository.CrudRepository;

public interface PollingRepository extends CrudRepository<Polling, Integer> {
    Polling findBySupIdAndStatus(int supplierId, boolean status);
}
