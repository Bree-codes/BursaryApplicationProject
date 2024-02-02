package com.bree.springproject.onlinebursaryapplication.models;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ExceptionModel {

    private HttpStatus httpStatus;

    private String message;

    private Date date;


}
