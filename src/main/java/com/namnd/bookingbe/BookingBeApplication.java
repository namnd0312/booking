package com.namnd.bookingbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookingBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingBeApplication.class, args);
    }

}
