package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class InvalidUpdateException extends RuntimeException {

    public InvalidUpdateException(String message) {
        super(message);
    }
}
