package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class FormAlreadySentException extends RuntimeException{

    public FormAlreadySentException(String message) {
        super(message);
    }
}
