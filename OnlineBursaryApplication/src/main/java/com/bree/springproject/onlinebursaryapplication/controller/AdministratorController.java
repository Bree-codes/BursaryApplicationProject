package com.bree.springproject.onlinebursaryapplication.controller;


import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/admin/")
@Slf4j
public class AdministratorController {

    @PostMapping("/create-higher-users")
    public ResponseEntity<ResponseModel> createPrivilegedUsers()
    {
        log.warn("Creating a privileged user");



        return null;
    }

}
