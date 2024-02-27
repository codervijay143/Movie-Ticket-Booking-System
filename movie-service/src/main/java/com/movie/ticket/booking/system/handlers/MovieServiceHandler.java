package com.movie.ticket.booking.system.handlers;


import com.movie.ticket.booking.system.commons.handler.GlobalExceptionHandler;
import com.movie.ticket.booking.system.dtos.ResponseErrorDTO;
import com.movie.ticket.booking.system.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class MovieServiceHandler extends GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseErrorDTO> movieNotFoundException(MovieNotFoundException exception){
        return new ResponseEntity<ResponseErrorDTO>(
                ResponseErrorDTO.builder()
                        .message(exception.getMessage())
                        .statusCodeDescription(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build(),HttpStatus.OK
        );
    }
}
