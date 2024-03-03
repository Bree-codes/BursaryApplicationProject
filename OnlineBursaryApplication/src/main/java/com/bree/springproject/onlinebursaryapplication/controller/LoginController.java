package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.models.AuthenticationResponseModel;
import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.service.HandleLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@Slf4j
public class LoginController {

    private final HandleLoginService handleLoginService;

    @Autowired
    public LoginController(HandleLoginService handlleLoginService) {
        this.handleLoginService = handlleLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseModel> login(
            @RequestBody LoginModel loginModel){
        log.info("received a login request");
       return ResponseEntity.ok(handleLoginService.login(loginModel));
    }
}

