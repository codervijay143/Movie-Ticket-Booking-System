package com.movie.ticket.booking.system.handlers;

import com.movie.ticket.booking.system.dtos.ResponseDTO;
import com.movie.ticket.booking.system.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiGatewayHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDTO> unauthorizedException(UnauthorizedException unauthorizedException){
        ResponseDTO responseDTO = ResponseDTO.builder()
                .errorMessage(unauthorizedException.getMessage())
                .statusCodeDescription(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
    }

}
