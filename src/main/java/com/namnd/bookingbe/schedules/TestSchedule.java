package com.namnd.bookingbe.schedules;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TestSchedule {

    @Scheduled(fixedRate = 15000) // 30 seconds (in milliseconds)
    public void runScheduledTask() throws InterruptedException {
        // Your task logic here

        System.out.println("Start Scheduled task executed!" + Instant.now());

        Thread.sleep(35000);
        System.out.println("------------------");
        System.out.println("End Scheduled task executed!" + Instant.now());
    }
}
