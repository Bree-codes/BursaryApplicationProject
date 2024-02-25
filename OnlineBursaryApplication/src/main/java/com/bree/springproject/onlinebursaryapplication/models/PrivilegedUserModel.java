package com.bree.springproject.onlinebursaryapplication.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegedUserModel
{
    private String username;

    private String phoneNumberOrEmail;

    private Role role;

}
