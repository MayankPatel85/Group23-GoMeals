package com.gomeals.controller;

import com.gomeals.model.Polling;
import com.gomeals.service.PollingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling")
@CrossOrigin(origins = "http://localhost:3000")
public class PollingController {

    @Autowired
    PollingService pollingService;

    @GetMapping("/get/{id}")
    public Polling getPollById(@PathVariable int id) {
        return pollingService.getPollById(id);
    }

    @GetMapping("/get/activePolls/{supId}")
    public List<Polling> getActivePollForSuppliers(@PathVariable int[] supId) {
        return pollingService.getActivePollForSupplier(supId);
    }

    @PostMapping("/create")
    public Polling createPoll(@RequestBody Polling polling) {
        return pollingService.createPoll(polling);
    }

    @PutMapping("/update")
    public Polling updatePoll(@RequestBody Polling polling) {
        return pollingService.updatePoll(polling);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePollById(@PathVariable int id) {
        return pollingService.deletePollById(id);
    }

    @GetMapping("/get/allPolls/{supId}")
    public List<Polling> getAllPolls(@PathVariable int supId) {
        return pollingService.getAllPollsForSupplier(supId);
    }
}
