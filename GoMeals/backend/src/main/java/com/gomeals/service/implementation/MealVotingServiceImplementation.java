package com.gomeals.service.implementation;

import com.gomeals.model.MealVoting;
import com.gomeals.repository.MealVotingRepository;
import com.gomeals.service.MealVotingService;
import org.springframework.stereotype.Service;

@Service
public class MealVotingServiceImplementation implements MealVotingService {

    private final MealVotingRepository mealVotingRepository;

    public MealVotingServiceImplementation(MealVotingRepository mealVotingRepository) {
        this.mealVotingRepository = mealVotingRepository;
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
    public MealVoting getMealVotingForCustomerByPollId(int pollId, int custId) {
        return mealVotingRepository.getMealVotingForCustomerByPollId(pollId,custId);
    }
}
