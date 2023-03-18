package com.gomeals.repository;

import com.gomeals.model.MealVoting;
import com.gomeals.model.MealVotingId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MealVotingRepository extends CrudRepository<MealVoting, MealVotingId> {

    MealVoting findByPollingId(int pollingId);

    @Query("SELECT m FROM MealVoting m WHERE m.pollingId=:pollId and m.customerId =:custId")
    MealVoting getMealVotingForCustomerByPollId(@Param("pollId") int pollId, @Param("custId") int custId);

}
