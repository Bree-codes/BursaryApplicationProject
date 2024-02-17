package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.PrivilegedUserModel;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private CommunicationService communicationService;

    public ResponseEntity<ResponseModel> createUser(Long adminId, PrivilegedUserModel privilegedUserModel) {

        log.info("Forwarded the request to create the new privileged user.");

        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();

        /*Check if it's an email or a phone number passed*/
        if(registerUserService.checkValidityOfPhoneNumber(privilegedUserModel.getPhoneNumberOrEmail())){
            userRegistrationTable.setPhoneNumber(privilegedUserModel.getPhoneNumberOrEmail());
            userRegistrationTable.setEmail(null);

            /*Here we are messaging the new user, the request to log in with default credentials
            * changes them and take-up their role in the application*/

        }
        else {
            userRegistrationTable.setEmail(privilegedUserModel.getPhoneNumberOrEmail());
            userRegistrationTable.setPhoneNumber("0123456789");

            /*Here we are emailing the new user, the request to log in with default credentials
             * changes them and take-up their role in the application*/


        }




        userRegistrationTable.setPassword(privilegedUserModel.getRole());
        userRegistrationTable.setUsername(privilegedUserModel.getUsername());

       // userRegistrationRepository.save()

        /*The Response Model*/
        ResponseModel responseModel = new ResponseModel();

        responseModel.setHttpStatus(HttpStatus.CREATED);
        responseModel.setDate(new Date());
        responseModel.setMessage("New Privileged User Created Successfully");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
