package com.gomeals.service.implementation;

import com.gomeals.model.Polling;
import com.gomeals.repository.PollingRepository;
import com.gomeals.service.PollingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollingServiceImplementation implements PollingService {

    @Autowired
    PollingRepository pollingRepository;

    @Override
    public Polling createPoll(Polling polling) {
        return pollingRepository.save(polling);
    }

    @Override
    public Polling getPollById(int id) {
        return pollingRepository.findById(id).orElse(null);
    }

    public List<Polling> getActivePollForSupplier(int[] supId) {
        return pollingRepository.getActivePollForSupplier(supId);
    }

    @Override
    public Polling updatePoll(Polling polling) {
        Polling currentPolling = pollingRepository.findById(polling.getPollId()).orElse(null);
        currentPolling.setPollDate(polling.getPollDate());
        currentPolling.setItem1(polling.getItem1());
        currentPolling.setItem2(polling.getItem2());
        currentPolling.setItem3(polling.getItem3());
        currentPolling.setItem4(polling.getItem4());
        currentPolling.setItem5(polling.getItem5());
        currentPolling.setStatus(polling.getStatus());
        pollingRepository.save(currentPolling);
        return currentPolling;
    }

    @Override
    public String deletePollById(int id) {
        pollingRepository.deleteById(id);
        return "Poll deleted!";
    }
}
