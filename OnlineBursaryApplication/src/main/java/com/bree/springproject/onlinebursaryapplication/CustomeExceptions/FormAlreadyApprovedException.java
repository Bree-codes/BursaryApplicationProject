package com.bree.springproject.onlinebursaryapplication.CustomeExceptions;

public class FormAlreadyApprovedException extends RuntimeException{

    public FormAlreadyApprovedException(String message) {
        super(message);
    }
}
