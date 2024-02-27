package com.movie.ticket.booking.system.service.api;

import com.movie.ticket.booking.system.commons.constants.LoggerConstants;
import com.movie.ticket.booking.system.commons.dto.BookingDTO;
import com.movie.ticket.booking.system.service.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingAPI {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        log.info(LoggerConstants.ENTERED_CONTROLLER_MESSAGE.getValue(), "creteBooking", this.getClass(), bookingDTO.toString());
        return this.bookingService.createBooking(bookingDTO);
    }

    @GetMapping
    public List<BookingDTO> getBookings(){
        return this.bookingService.getBookings().stream().sorted(Comparator.comparing(BookingDTO::getCreatedDateTime).reversed()).collect(Collectors.toList());
    }

}
