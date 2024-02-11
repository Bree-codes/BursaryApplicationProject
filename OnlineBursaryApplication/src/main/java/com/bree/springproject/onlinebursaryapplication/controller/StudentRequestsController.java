package com.bree.springproject.onlinebursaryapplication.controller;


import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import com.bree.springproject.onlinebursaryapplication.service.HandleStudentRequestsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v0/student")
public class StudentRequestsController {

    @Autowired
    HandleStudentRequestsService handleStudentRequestsService;


    @PutMapping("/update-form-field")
    public ResponseEntity<String> updateFieldValue(
            @RequestBody StudentFormValues studentFormValues
            ){

        log.info("Received a request to update a form field");

        //forwarding the values to the backend.
        return handleStudentRequestsService.updateValues(studentFormValues);
    }

    @PostMapping("/save-form/{userId}")
    public ResponseEntity<String> storeFormFields(
            @PathVariable Long userId,
            @RequestBody Map<Long, String> fieldIdAndValue
    )
    {
        log.info("Received a request to save for to the database");

        //moving forward to format and store the form.
        return handleStudentRequestsService.saveFormValues(userId, fieldIdAndValue);
    }

    @GetMapping("/get-user-values")
    public ResponseEntity<List<List<StudentFormAndValuesModel>>> getLatestFormValues(
            @RequestParam Long userId
    )
    {
        return handleStudentRequestsService.getBindLatestFormAndValues(userId);
    }


}
