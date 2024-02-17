package com.bree.springproject.onlinebursaryapplication.service;


import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.FormAlreadySentException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UnauthorisedRequestException;
import com.bree.springproject.onlinebursaryapplication.Entity.ApprovedFormsEntity;
import com.bree.springproject.onlinebursaryapplication.Entity.UserNotificationTable;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.ChiefRequestRepository;
import com.bree.springproject.onlinebursaryapplication.repository.FormApprovalRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserNotificationsRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ViewLogicService {

    private UserRegistrationRepository userRegistrationRepository;

    private HandleChiefLogicService handleChiefLogicService;

    private ChiefRequestRepository chiefRequestRepository;

    private FormApprovalRepository formApprovalRepository;

    private UserNotificationsRepository userNotificationsRepository;

    @Autowired
    public void setUserRegistrationRepository(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Autowired
    public void setHandleChiefLogicService(HandleChiefLogicService handleChiefLogicService) {
        this.handleChiefLogicService = handleChiefLogicService;
    }

    @Autowired
    public void setChiefRequestRepository(ChiefRequestRepository chiefRequestRepository) {
        this.chiefRequestRepository = chiefRequestRepository;
    }

    @Autowired
    public void setFormApprovalRepository(FormApprovalRepository formApprovalRepository) {
        this.formApprovalRepository = formApprovalRepository;
    }

    @Autowired
    public void setUserNotificationsRepository(UserNotificationsRepository userNotificationsRepository) {
        this.userNotificationsRepository = userNotificationsRepository;
    }

    public ResponseEntity<List<Long>> getAvailableForms(Long viewerId) {
        log.info("Forwarded the request to find available forms");

        //check if the id belongs to a viewer.
         UserRegistrationTable userRegistrationTable =
                 userRegistrationRepository.findById(viewerId).get();

         //checking if it is a viewer's or admins id.
         if(!(userRegistrationTable.getRole().equalsIgnoreCase("view") ||
            userRegistrationTable.getRole().equalsIgnoreCase("admin")))
        {
            throw new UnauthorisedRequestException("The Id passed Does Not Belong to a viewer");
        }

         //moving forward to get the userId for the available forms.

                //find the latest bursary
         int latestBursary = handleChiefLogicService.latestFinder();

         //find the bursary the passed the chief's verification
        List<Long> userIds = chiefRequestRepository.findAllByStatus(true, String.valueOf(latestBursary));

        return new ResponseEntity<>(userIds, HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> approveForm(Boolean status, Long formUserId, String message) {

        //get the bursary month.
        int bursaryMonth = handleChiefLogicService.latestFinder();


        /*Check if the form had already been approved.*/
        if(formApprovalRepository.findAllByUserIdAndBursaryMonth(
                formUserId, String.valueOf(bursaryMonth)) != null)
        {
            /*throw an exception here.*/
            throw  new FormAlreadySentException("The Form You Are Trying To Approve Has Already Been Approved");
        }

        /*Here we need to update the status at the chief's table to identify the form as approved*/
        chiefRequestRepository.updateStatusByUserIdAndBursaryMonth(formUserId, String.valueOf(bursaryMonth));

        /*Here we need to message the user.*/
        UserNotificationTable userNotificationTable = new UserNotificationTable();

        userNotificationTable.setUserId(formUserId);
        userNotificationTable.setMessage(message);

        userNotificationsRepository.save(userNotificationTable);
        log.info("Update the user notification");


        ApprovedFormsEntity approvedFormsEntity = new ApprovedFormsEntity();

        approvedFormsEntity.setUserId(formUserId);
        approvedFormsEntity.setStatus(status);
        approvedFormsEntity.setBursaryMonth(String.valueOf(bursaryMonth));

        //saving the field to the database;
        formApprovalRepository.save(approvedFormsEntity);
        log.info("Saved the approval repository");


        /*Preparing the response.*/
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("Form Approval Successful");
        responseModel.setDate(new Date());
        responseModel.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }
}
