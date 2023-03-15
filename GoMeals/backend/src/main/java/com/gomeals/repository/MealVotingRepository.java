package com.gomeals.repository;

import com.gomeals.model.MealVoting;
import com.gomeals.model.MealVotingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealVotingRepository extends CrudRepository <MealVoting, MealVotingId> {

    MealVoting findByPollingId(int pollingId);
    @Query(value = "SELECT mv.voted_meal, COUNT(mv.voted_meal) AS votes " +
            "FROM meal_voting mv " +
            "WHERE mv.polling_id = :pollingId AND mv.supplier_id = :supplierId " +
            "GROUP BY mv.voted_meal, mv.polling_id, mv.supplier_id " +
            "ORDER BY COUNT(mv.voted_meal) DESC LIMIT 1",nativeQuery = true)
    List<Object[]> countMealVotes(@Param("pollingId") int pollingId, @Param("supplierId") int supplierId);
}
