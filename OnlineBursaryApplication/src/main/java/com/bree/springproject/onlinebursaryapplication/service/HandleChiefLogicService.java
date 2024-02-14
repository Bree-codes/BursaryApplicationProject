package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.FormAlreadySentException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.NoFormAvailableException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserFieldDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserNotAChiefException;
import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.ChiefRequestRepository;
import com.bree.springproject.onlinebursaryapplication.repository.StudentsValueRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class HandleChiefLogicService {

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    ChiefRequestRepository chiefRequestRepository;

    @Autowired
    CreateFormService createFormService;

    @Autowired
    StudentsValueRepository studentsValueRepository;
    ResponseModel responseModel;


    public ResponseEntity<ResponseModel> receiveUserApplicationForm(ChiefDataEntity chiefDataEntity)
    {
        //check if the user form exists.
        if(studentsValueRepository.findAllByUserIdAndBursaryMonthOrderByBursaryMonth(
                chiefDataEntity.getUserId(),
                chiefDataEntity.getBursaryMonth()) == null)
        {
            throw  new NoFormAvailableException("The Form You Attempted To Send Does Not Exists");
        }

        log.info("Received the sent form");

        //encoding the bursary month.
        chiefDataEntity.setBursaryMonth(
                createFormService.encoder(
                        chiefDataEntity.getBursaryMonth(), 0)
        );

        //checking if the form was already sent.
        log.info("Check If Already Received for Consenting.");
        if(chiefRequestRepository.findByUserIdAndBursaryMonth(chiefDataEntity.getUserId(),
                chiefDataEntity.getBursaryMonth()) != null)
        {
            throw new FormAlreadySentException("Sending Form Twice Is Not Allowed.");
        }

        //check if the chief exist.
        log.info("Validating the request");

        UserRegistrationTable userRegistrationTable =
                userRegistrationRepository.findByUsername(chiefDataEntity.getChiefUserName());

        //check if the chief entered is register in the application
        if(userRegistrationTable == null)
        {
            throw new UserFieldDoesNotExistException("The Chief Entered Is Not Register Yet");
        }
        //check if the username entered belongs to a chef
        if(!userRegistrationTable.getRole().equalsIgnoreCase("chief"))
        {
            throw  new UserNotAChiefException("User userName Entered Does not Belong To A Chief");
        }


        log.info("Moving forward to save the chief");
        chiefRequestRepository.save(chiefDataEntity);

        //giving the response after insertion
        responseModel = new ResponseModel();
        responseModel.setDate(new Date());
        responseModel.setMessage("Form Sent Successfully.");
        responseModel.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
