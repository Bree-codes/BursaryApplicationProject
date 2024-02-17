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

    private Long userId;
    private String username;
    private String email;
    private String phoneNumber;
    private Boolean status;
    private String role;
}
