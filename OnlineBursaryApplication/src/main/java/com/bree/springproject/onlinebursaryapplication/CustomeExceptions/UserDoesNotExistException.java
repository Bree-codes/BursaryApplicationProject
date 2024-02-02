package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
