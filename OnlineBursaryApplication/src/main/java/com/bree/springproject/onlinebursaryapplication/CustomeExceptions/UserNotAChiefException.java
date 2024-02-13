package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UserNotAChiefException extends RuntimeException{

    public UserNotAChiefException(String message) {
        super(message);
    }
}
