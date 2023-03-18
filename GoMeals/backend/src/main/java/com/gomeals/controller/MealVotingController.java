package com.gomeals.controller;

import com.gomeals.model.Complain;
import com.gomeals.model.MealVoting;
import com.gomeals.service.MealVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal-voting")
@CrossOrigin(origins = "http://localhost:3000")
public class MealVotingController {

    private final MealVotingService mealVotingService;

    public MealVotingController(MealVotingService mealVotingService) {
        this.mealVotingService = mealVotingService;
    }

    @PostMapping("/create")
    public ResponseEntity<MealVoting> createMealVoting(@RequestBody MealVoting mealVoting) {
        return new ResponseEntity<>(mealVotingService.createMealVoting(mealVoting), HttpStatus.CREATED);
    }

    @GetMapping("/get/{poll-id}")
    public ResponseEntity<MealVoting> getMealVotingByPollId(@PathVariable("poll-id") int pollId) {
        MealVoting mealVoting = mealVotingService.getMealVotingByPollingId(pollId);
        if (mealVoting == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mealVoting, HttpStatus.OK);
    }

    @GetMapping("/get/{pollId}/{custId}")
    @ResponseBody
    public ResponseEntity<MealVoting> getMealVotingForCustomerByPollId(@PathVariable("pollId") int pollId,
            @PathVariable("custId") int custId) {
        MealVoting mealVoting = mealVotingService.getMealVotingForCustomerByPollId(pollId, custId);
        if (mealVoting == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            // return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(mealVoting, HttpStatus.OK);
    }

}
