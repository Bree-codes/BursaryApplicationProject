package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class NoFormAvailableException extends RuntimeException{

    public NoFormAvailableException(String message) {
        super(message);
    }
}
