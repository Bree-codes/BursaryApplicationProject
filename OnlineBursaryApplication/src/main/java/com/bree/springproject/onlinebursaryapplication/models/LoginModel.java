package com.bree.springproject.onlinebursaryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginModel {

    private String email;
    private String phoneNumber;
    private String role;
    private Boolean status;
    private Long userId;
    private String username;


}
