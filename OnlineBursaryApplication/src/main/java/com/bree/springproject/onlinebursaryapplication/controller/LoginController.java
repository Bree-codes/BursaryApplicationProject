package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<LoginModel> login(@RequestParam String username,@RequestParam String password){
        log.info("Received login request");
        return loginService.loginUser(username,password);
    }


}

