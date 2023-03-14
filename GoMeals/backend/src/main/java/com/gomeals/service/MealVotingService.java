package com.gomeals.service;

import com.gomeals.model.MealVoting;

public interface MealVotingService {
    MealVoting createMealVoting(MealVoting mealVoting);
    MealVoting getMealVotingByPollingId(int pollingId);
}
