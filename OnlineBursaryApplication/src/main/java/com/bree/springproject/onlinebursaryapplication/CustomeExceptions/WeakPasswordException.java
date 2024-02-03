package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class WeakPasswordException extends RuntimeException{
    public WeakPasswordException(String message) {
        super(message);
    }
}
