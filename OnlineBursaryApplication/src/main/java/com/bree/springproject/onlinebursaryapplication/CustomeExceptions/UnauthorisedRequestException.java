package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UnauthorisedRequestException extends RuntimeException{

    public UnauthorisedRequestException(String message) {
        super(message);
    }
}
