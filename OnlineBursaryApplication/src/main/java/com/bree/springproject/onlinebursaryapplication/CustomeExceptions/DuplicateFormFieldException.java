package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class DuplicateFormFieldException extends RuntimeException{

    public DuplicateFormFieldException(String message) {
        super(message);
    }
}
