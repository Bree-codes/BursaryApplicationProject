package com.bree.springproject.onlinebursaryapplication.controller;


import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.service.RegisterUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/home")
@Setter
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationTable userRegistrationTable){
        log.info("Receive a request to create a user.");

        //verify the user-email


        return  registerUserService.registrationValidation(userRegistrationTable);

    }

    @PutMapping
    public ResponseEntity<String> changePassword(@RequestParam String userPassword)
    {
        log.info("Received a request to change the password");

        //

        return registerUserService.updatePassword(userPassword);
    }

    @GetMapping("/forgotten-password/")
    public ResponseEntity<String> forgottenPassword(@RequestParam String userName)
    {
        log.info("User forgotten password");

        //forward the request to send the email to the user for changing the password.
        registerUserService.changePassword(userName);

    }


}
