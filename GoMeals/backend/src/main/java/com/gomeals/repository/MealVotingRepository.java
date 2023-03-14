package com.gomeals.repository;

import com.gomeals.model.MealVoting;
import com.gomeals.model.MealVotingId;
import org.springframework.data.repository.CrudRepository;

public interface MealVotingRepository extends CrudRepository <MealVoting, MealVotingId> {

    MealVoting findByPollingId(int pollingId);

}
