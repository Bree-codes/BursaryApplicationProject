package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.service.ViewLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/views/")
@Slf4j
public class ViewFormsController {

    @Autowired
    ViewLogicService viewLogicService;

    @GetMapping("/view-forms/{viewerId}")
    public ResponseEntity<List<Long>> viewAvailableForms(
            @PathVariable Long viewerId)
    {
        log.info("Received A Request Form A viewer To Get Consented Froms");
        return viewLogicService.getAvailableForms(viewerId);
    }

    public ResponseEntity<ResponseModel> ApproveForm(Boolean status, Long formUserId, String message)
    {
        log.info("Received a request to approve a form");

        return viewLogicService.approveForm(status, formUserId, message);
    }
}
