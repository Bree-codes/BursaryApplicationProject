package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class FieldValuesAlreadyExistException extends RuntimeException{

    public FieldValuesAlreadyExistException(String message) {
        super(message);
    }
}
