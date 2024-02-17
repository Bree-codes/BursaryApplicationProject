package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class UserRequestNotAuthorised extends RuntimeException{

    public UserRequestNotAuthorised(String message) {
        super(message);
    }
}
