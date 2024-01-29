package com.bree.springproject.bursaryapplication.controller;

import com.bree.springproject.bursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.bursaryapplication.Models.UserRegistrationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")

public class RegisterUserController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationModel userRegistrationModel){


        return null;
    }


}
