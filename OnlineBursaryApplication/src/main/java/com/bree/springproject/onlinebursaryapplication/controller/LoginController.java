package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.models.AuthenticationResponseModel;
import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.service.HandlleLoginService;
import com.bree.springproject.onlinebursaryapplication.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class LoginController {

    private final HandlleLoginService handlleLoginService;

    @Autowired
    public LoginController(HandlleLoginService handlleLoginService) {
        this.handlleLoginService = handlleLoginService;
    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponseModel> login(
            @RequestBody LoginModel loginModel){
        log.info("received a login request");
       return ResponseEntity.ok(handlleLoginService.login(loginModel));
    }
}

