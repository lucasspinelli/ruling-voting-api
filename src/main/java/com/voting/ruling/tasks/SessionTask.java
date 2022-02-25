package com.voting.ruling.tasks;


import com.voting.ruling.model.Session;
import com.voting.ruling.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionTask {

    @Autowired
    SessionService sessionService;

    @Scheduled(cron = "${app.tasks.ruling.status}")
    public void rulingSession() {
        List<Session> sessionList = sessionService.findSessionByActive(true);
        sessionList
                .stream()
                .filter(session -> sessionService.verifyExpired(session))
                .forEach(session -> sessionService.changeActive(session, false));
    }
}
