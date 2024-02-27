package com.hunger.saviour.portal.handlers;

import com.hunger.saviour.portal.dtos.ResponseDTO;
import com.hunger.saviour.portal.dtos.ResponseErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class UserServiceAPIHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.info("Entered into UserAPIHandler class with exception : "+ exception.getMessage());
        return new ResponseEntity<ResponseErrorDTO>(ResponseErrorDTO.builder()
                .error_messages(exception.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList())
//                .exception_message(exception.getMessage())
                .statusCodeDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameExist.class)
    public ResponseEntity<ResponseErrorDTO> usernameExistException(UsernameExist exception){
        log.info("Entered into usernameExistException class with exception : "+ exception.getMessage());
        return new ResponseEntity<ResponseErrorDTO>(ResponseErrorDTO.builder()
                .exception_message(exception.getMessage())
                .statusCodeDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.OK);
    }
}
