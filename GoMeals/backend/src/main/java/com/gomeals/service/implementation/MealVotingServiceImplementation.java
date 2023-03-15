package com.gomeals.service.implementation;

import com.gomeals.model.MealVoting;
import com.gomeals.model.Polling;
import com.gomeals.repository.MealVotingRepository;
import com.gomeals.repository.PollingRepository;
import com.gomeals.service.MealVotingService;
import com.gomeals.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealVotingServiceImplementation implements MealVotingService {

    private final MealVotingRepository mealVotingRepository;
    private final PollingRepository pollingRepository;

    public MealVotingServiceImplementation(MealVotingRepository mealVotingRepository, PollingRepository pollingRepository) {
        this.mealVotingRepository = mealVotingRepository;
        this.pollingRepository = pollingRepository;
    }
    @Override
    public MealVoting createMealVoting(MealVoting mealVoting) {
        return mealVotingRepository.save(mealVoting);
    }

    @Override
    public MealVoting getMealVotingByPollingId(int pollingId) {
        return mealVotingRepository.findByPollingId(pollingId);
    }

    @Override
    public String findMostVotedMeal(int pollingId, int supplierId){
        List<Object[]> mostVotedMeals = mealVotingRepository.countMealVotes(pollingId,supplierId);
        if (mostVotedMeals.isEmpty()){
            System.out.println("No voted meals were found.");
            return null;
        }
        String mostVotedMeal = null;
        for (Object[] object : mostVotedMeals) {
            mostVotedMeal  = (String) object[0];
            Long voteCount = (Long) object[1];
        }
        Polling polling = pollingRepository.findById(pollingId).orElse(null);
        if(polling == null){
            System.out.println("Poll does not exist, can't update the special meal.");
            return null;
        }
        polling.setVote(mostVotedMeal);
        pollingRepository.save(polling);

        return mostVotedMeal;
    }
}
