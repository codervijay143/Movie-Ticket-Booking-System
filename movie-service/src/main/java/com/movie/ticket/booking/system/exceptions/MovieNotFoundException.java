package com.movie.ticket.booking.system.exceptions;

public class MovieNotFoundException extends Exception{

    private String message;

    public MovieNotFoundException(String message){
        super(message);
    }
}
