package com.bree.springproject.onlinebursaryapplication.controller.ViewsController;

import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/views/")
@Slf4j
public class ViewFormsController {

    @GetMapping("/view-forms")
    public ResponseEntity<StudentFormAndValuesModel> viewForm()
    {


        return null;
    }
}
