package com.bree.springproject.onlinebursaryapplication.controller;

import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.service.ViewLogicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/views/")
@Slf4j
@CrossOrigin("http://localhost:5173/")
public class ViewFormsController {

    ViewLogicService viewLogicService;

    @Autowired
    public void setViewLogicService(ViewLogicService viewLogicService) {
        this.viewLogicService = viewLogicService;
    }

    @GetMapping("/view-forms/{viewerId}")
    public ResponseEntity<List<Long>> viewAvailableForms(
            @PathVariable Long viewerId)
    {
        log.info("Received A Request Form A viewer To Get Consented Froms");
        return viewLogicService.getAvailableForms(viewerId);
    }

    @PutMapping("/approve-form")
    public ResponseEntity<ResponseModel> ApproveForm(
            @Valid @NotNull Boolean status,
            @Valid @NotNull Long formUserId,
            @Valid @NotNull String message)
    {
        log.info("Received a request to approve a form");

        return viewLogicService.approveForm(status, formUserId, message);
    }
}
