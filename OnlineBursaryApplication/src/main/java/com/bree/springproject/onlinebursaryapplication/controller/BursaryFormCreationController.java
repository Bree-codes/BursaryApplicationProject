package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.service.CreateFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/admin/student")
@Slf4j
@CrossOrigin("http://localhost:5173/")
public class BursaryFormCreationController {

    @Autowired
    CreateFormService createFormService;

    /*
    * This API accepts a section of the form.(eg Section A)*/
    @PostMapping("/create-form/{section}")
    public ResponseEntity<String> createForm(@PathVariable String section,
                                             @RequestBody Map<String, String> formSectionA,
                                             @RequestParam String month,
                                             @RequestParam Long userId)
    {
        log.info("Receive a request to create a form");
        //here the administrator will fill the field names required for section A.
        return createFormService.createSectionA(formSectionA, month, userId, section);
    }

    @PutMapping("/update-form")
    public ResponseEntity<String> updateForm(@RequestBody
                                             ApplicationFormCreateTable updatedSection)
    {
        log.info("Received a request update the form");

        return createFormService.updateForm(updatedSection);
    }

    @GetMapping("/get-form")
    public ResponseEntity<List<List<ApplicationFormCreateTable>>> getForm()
    {
        //here we will get a list of the list of fields for each section.

        log.info("Received a request to get the form fields");

        return createFormService.getForm();
    }


    @GetMapping("/get-form/by-year")
    public  ResponseEntity<List<List<ApplicationFormCreateTable>>> getFormByYear(
            @RequestParam String month,
            @RequestParam String year)
    {

        log.info("Received a request to get a form by year.");

        return createFormService.getForm(month, year);
    }


}
