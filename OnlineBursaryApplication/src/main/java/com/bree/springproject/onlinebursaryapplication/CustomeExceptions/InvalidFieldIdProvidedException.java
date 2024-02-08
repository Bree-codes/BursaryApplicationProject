package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

import lombok.experimental.StandardException;

public class InvalidFieldIdProvidedException extends RuntimeException{
    public InvalidFieldIdProvidedException(String message) {
        super(message);
    }
}
