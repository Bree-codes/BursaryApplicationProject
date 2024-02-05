package com.bree.springproject.onlinebursaryapplication.controller;

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

    @PostMapping("form/{section}")
    public ResponseEntity<String> createSectionA( @PathVariable String section,
            @RequestBody Map<String, String> formSectionA,
            @RequestParam String month, @RequestParam Long userId)
    {
        //here the administrator will fill the field names required for section A.
        return createFormService.createSectionA(formSectionA, month, userId, section);
    }


}
