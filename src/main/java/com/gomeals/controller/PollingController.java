package com.gomeals.controller;

import com.gomeals.model.Polling;
import com.gomeals.service.PollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling")
public class PollingController {

    @Autowired
    PollingService pollingService;

    @GetMapping("/get/{id}")
    public Polling getPollById(@PathVariable int id) {
        return pollingService.getPollById(id);
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
}
