package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class FormNotFoundException extends RuntimeException{

    public FormNotFoundException(String message) {
        super(message);
    }
}
