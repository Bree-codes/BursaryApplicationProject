package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.service.CreateFormService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/create-form")
@Slf4j
public class BursaryFormCreationController {

    @Autowired
    CreateFormService createFormService;

    @PostMapping("create-form/{section}")
    public ResponseEntity<String> createForm( @PathVariable String section,
            @RequestBody Map<String, String> formSectionA,
            @RequestParam String month, @RequestParam Long userId)
    {
        //here the administrator will fill the field names required for section A.
        return createFormService.createSectionA(formSectionA, month, userId, section);
    }

    @PutMapping("update-form/")
    public ResponseEntity<String> updateForm(@RequestBody
                                             List<ApplicationFormCreateTable> updatedSection)
    {
        return createFormService.updateForm(updatedSection);
    }


}
