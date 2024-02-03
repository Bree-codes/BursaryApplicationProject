package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
