package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserFieldDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserNotAChiefException;
import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.ChiefRequestRepository;
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

    ResponseModel responseModel;

    public ResponseEntity<ResponseModel> receiveUserApplicationForm(ChiefDataEntity chiefDataEntity)
    {
        log.info("Received the sent form");

        //check if the chief exist.
        log.info("Validating the request");

        UserRegistrationTable userRegistrationTable =
                userRegistrationRepository.findByUsername(chiefDataEntity.getChiefUserName());

        if(userRegistrationTable == null)
        {
            throw new UserFieldDoesNotExistException("The Chief Entered Is Not Register Yet");
        }

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
