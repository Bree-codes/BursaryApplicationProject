package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UserExistException extends RuntimeException{

    public UserExistException(String message) {
        super(message);
    }
}
