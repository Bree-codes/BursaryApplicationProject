package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import com.bree.springproject.onlinebursaryapplication.service.HandleChiefLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/chief/")
@Slf4j
@CrossOrigin("http://localhost:5173/")
public class ChiefsRequestController {

    @Autowired
    HandleChiefLogicService handleChiefLogicService;
    /*Get mapping to get form paths for all students who have committed
    * their forms to the chief for consenting.*/
    @GetMapping("/get-forms/{chiefId}")
    public ResponseEntity<List<ChiefDataEntity>> getSentForms(
            @PathVariable Long chiefId
    )
    {
        log.info("Received a request to get for for a chief.");

        return handleChiefLogicService.getForms(chiefId);
    }

    @PutMapping("/consent")
    public ResponseEntity<String> consentUserValues(
            @RequestParam Long userId,
            @RequestParam Boolean status,
            @RequestParam String bursaryMonth
    )
    {

        log.info("received a request to consent user values.");

        //forwarding the status.

        return handleChiefLogicService.consent(userId, status, bursaryMonth);
    }
}
