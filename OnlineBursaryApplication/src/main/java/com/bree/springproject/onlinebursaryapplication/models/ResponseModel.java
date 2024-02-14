package com.bree.springproject.onlinebursaryapplication.models;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ResponseModel {

    String message;

    HttpStatus httpStatus;

    Date date;

}
