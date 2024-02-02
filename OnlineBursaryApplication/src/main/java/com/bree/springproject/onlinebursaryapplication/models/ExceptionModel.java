package com.bree.springproject.onlinebursaryapplication.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ExceptionModel {

    private String message;

    private Date date;

}
