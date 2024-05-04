package com.example.EcoCity.schedulers;

import com.example.EcoCity.models.enums.EventStatus;
import com.example.EcoCity.services.db.DBServiceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class ChangeEventStatusScheduler {
    public DBServiceEvent dbServiceEvent;

    @Autowired
    public ChangeEventStatusScheduler(DBServiceEvent dbServiceEvent) {
        this.dbServiceEvent = dbServiceEvent;
    }

    @Scheduled(cron = "1 * * * * *")
    public void changeEventsStatus() {
        LocalDateTime now = LocalDateTime.now();
        dbServiceEvent.findAll()
                .stream()
                .filter(e -> Objects.equals(e.getStatus(), EventStatus.WAITING_START))
                .forEach(e -> {
                    if (e.getEndDate().equals(now)) {
                        e.setStatus(EventStatus.FINISHED);
                    } else if (e.getStartDate().equals(now)) {
                        e.setStatus(EventStatus.UNDERWAY);
                    }
                    dbServiceEvent.update(e);
                });
    }
}
