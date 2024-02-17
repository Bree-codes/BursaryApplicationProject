package com.bree.springproject.onlinebursaryapplication.controller;


import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/admin/")
@Slf4j
public class AdministratorController {

    @Autowired
    AdminService adminService;

    @PostMapping("/create-higher-users")
    public ResponseEntity<ResponseModel> createPrivilegedUsers(

    )
    {
        log.warn("Request to create a privileged user");



        return null;
    }

}
