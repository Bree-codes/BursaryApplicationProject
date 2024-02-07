package com.bree.springproject.onlinebursaryapplication.controller;


import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.service.HandleStudentRequestsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v0/student")
public class StudentRequestsController {

    @Autowired
    HandleStudentRequestsService handleStudentRequestsService;



    /*
    * NOTE
    * This API will handle both first time storage, additional storage */

    @PostMapping("/submit-form")
    public ResponseEntity<String> storeValue(
            @RequestBody StudentFormValues studentFormValues
            ){

        log.info("Received a request to store student data o the backend");

        //forwarding the values to the backend.
        return handleStudentRequestsService.saveValues(studentFormValues);
    }




}
