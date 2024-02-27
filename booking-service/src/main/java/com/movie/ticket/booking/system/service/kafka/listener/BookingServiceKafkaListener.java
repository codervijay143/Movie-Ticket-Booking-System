package com.movie.ticket.booking.system.service.kafka.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceKafkaListener {

    private final BookingService bookingService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-response", groupId = "payment-response-booking")
    public void receiveFromPaymentResponseTopic(String bookingDTOJson) {
        log.info("Received confirmation on booking details from payment-response kafka topic");
        try {
            BookingDTO bookingDTO = objectMapper.readValue(bookingDTOJson, BookingDTO.class);
            this.bookingService.processBooking(bookingDTO);
        } catch (JsonProcessingException e) {
            log.error("Error while receiving confirmation on booking details from " +
                    "payment-response kafka topic");
        }
    }
}