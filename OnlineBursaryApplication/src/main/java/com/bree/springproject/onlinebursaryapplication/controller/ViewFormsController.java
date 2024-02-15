package com.bree.springproject.onlinebursaryapplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/views/")
@Slf4j
public class ViewFormsController {

    @GetMapping("/view-forms/{viewerId}")
    public ResponseEntity<List<Long>> viewAvailableForms(
            @PathVariable String viewerId)
    {

        return null;
    }
}
