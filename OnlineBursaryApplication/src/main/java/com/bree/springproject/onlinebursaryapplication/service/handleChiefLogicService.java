package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserFieldDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.ChiefRequestRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class handleChiefLogicService {

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    ChiefRequestRepository chiefRequestRepository;

    public ResponseEntity<ResponseModel> receiveUserApplicationForm(ChiefDataEntity chiefDataEntity)
    {
        log.info("Received the sent form");

        //check if the chief exist.
        log.info("Validating the request");
        if(userRegistrationRepository.findByUsername(chiefDataEntity.getChiefUserName()) == null)
        {
            throw new UserFieldDoesNotExistException("The Chief Entered Is Not Register Yet");
        }


    }
}
