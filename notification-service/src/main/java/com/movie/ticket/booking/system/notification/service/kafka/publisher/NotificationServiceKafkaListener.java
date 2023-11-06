package com.movie.ticket.booking.system.notification.service.kafka.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

    @Service
    @Slf4j
    @RequiredArgsConstructor
    public class NotificationServiceKafkaListener {
        @Autowired
        private ObjectMapper objectMapper;
        @Autowired
        private  JavaMailSender javaMailSender;

        @KafkaListener(topics = "payment-response", groupId = "paymentresponse-notification")
        public void receiveFromPaymentResponseTopic(String bookingDTOJson) {
            log.info("Received confirmation on booking details from payment-response kafka topic");
            try {
                BookingDTO bookingDTO = objectMapper.readValue(bookingDTOJson, BookingDTO.class);
                sendEmail(bookingDTO);
                log.info("Successfully sent Booking Details to EmailId");
            } catch (JsonProcessingException e) {
                log.error("Error while receiving confirmation on booking details from " +
                        "payment-response kafka topic");
            }
        }
        public void sendEmail(BookingDTO bookingDTO) {
            // Create an email message with the extracted information
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("vk9677022@gmail.com");
            message.setSubject("Booking Confirmation");
            message.setText("Booking details: " + bookingDTO.toString()); // Customize the email content as needed.

            // Send the email
            javaMailSender.send(message);
        }
    }

