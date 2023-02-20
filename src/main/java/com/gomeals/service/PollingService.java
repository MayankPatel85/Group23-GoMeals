package com.gomeals.service;

import com.gomeals.model.Polling;

public interface PollingService {

    Polling createPoll(Polling polling);

    Polling getPollById(int id);

    Polling updatePoll(Polling polling);

    String deletePollById(int id);
}
