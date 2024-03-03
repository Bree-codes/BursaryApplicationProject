package com.bree.springproject.onlinebursaryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseModel {

    private  String message;

    private HttpStatus httpStatus;

    private Date date;

    private String token;
}
