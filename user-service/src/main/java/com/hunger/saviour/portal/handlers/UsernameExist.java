package com.hunger.saviour.portal.handlers;

public class UsernameExist extends Exception{
    private String message;
    public UsernameExist(String message){
        super(message);
    }
}
