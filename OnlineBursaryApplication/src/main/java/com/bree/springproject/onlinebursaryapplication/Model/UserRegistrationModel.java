package com.bree.springproject.onlinebursaryapplication.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserRegistrationModel {

    private String username;

    private String email;

    private String password;


}