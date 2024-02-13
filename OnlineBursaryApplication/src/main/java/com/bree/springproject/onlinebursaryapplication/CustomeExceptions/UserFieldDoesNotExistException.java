package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UserFieldDoesNotExistException extends RuntimeException{

    public UserFieldDoesNotExistException(String message) {
        super(message);
    }
}
