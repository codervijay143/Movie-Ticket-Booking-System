package com.movie.ticket.booking.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableDiscoveryClient
public class MovieTicketBookingSystemApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(MovieTicketBookingSystemApiGateway.class,args);
    }
}