package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserRequestNotAuthorised;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.PrivilegedUserModel;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.FormApprovalRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private FormApprovalRepository formApprovalRepository;

    public ResponseEntity<ResponseModel> createUser(Long adminId, PrivilegedUserModel privilegedUserModel) {

        log.info("Forwarded the request to create the new privileged user.");

        /*Confirming that it is the administrator who sent this request*/
        if(!userRegistrationRepository.findById(adminId).get().getRole().equalsIgnoreCase("admin"))
        {
            throw new UserRequestNotAuthorised("User Not An Administrator.");
        }

        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();
        String defaultPassword = privilegedUserModel.getRole();

        /*Creating the new privileged user.*/

        userRegistrationTable.setPassword(defaultPassword);
        userRegistrationTable.setUsername(privilegedUserModel.getUsername());
        userRegistrationTable.setRole(privilegedUserModel.getRole());


        /*Check if it's an email or a phone number passed*/
        if(registerUserService.checkValidityOfPhoneNumber(privilegedUserModel.getPhoneNumberOrEmail())){
            userRegistrationTable.setPhoneNumber(privilegedUserModel.getPhoneNumberOrEmail());
            userRegistrationTable.setEmail(null);

            /*The user may have entered the phone number
            so the email should remain null and will be updated by the created user.*/
            userRegistrationTable.setPhoneNumber(privilegedUserModel.getPhoneNumberOrEmail());
            userRegistrationTable.setEmail(null);
            /*Here we are messaging the new user, the request to log in with default credentials
            * changes them and take-up their role in the application*/

        }
        else {
            userRegistrationTable.setEmail(privilegedUserModel.getPhoneNumberOrEmail());
            userRegistrationTable.setPhoneNumber("0123456789");

            /*The phone number is a non-null field, so we are setting the random number as the default phone number to
            * be updated later by the created user.*/
            userRegistrationTable.setPhoneNumber("0123456789");
            userRegistrationTable.setEmail(privilegedUserModel.getPhoneNumberOrEmail());

            /*Here we are emailing the new user, the request to log in with default credentials
             * changes them and take-up their role in the application*/
            communicationService.emailPrivilegedUsers(
                    privilegedUserModel.getPhoneNumberOrEmail(),
                    privilegedUserModel.getUsername(),
                    defaultPassword
                    );
        }

        /*Saving the new privileged user to the database.*/

            //before inserting the new user, we need to ensure that the user does not already exist
        if(userRegistrationRepository.findByUsername(privilegedUserModel.getUsername()) != null){

            log.error("Duplicate user name exception");
            throw  new UserExistException("The Username Entered Is Already Assign To another Person");
        }

        log.info("request handled successfully");
        /*Performing the insertion*/
        userRegistrationRepository.save(userRegistrationTable);

        /*Preparing the response to the performed process*/
        ResponseModel responseModel = new ResponseModel();

        responseModel.setHttpStatus(HttpStatus.CREATED);
        responseModel.setDate(new Date());
        responseModel.setMessage("New Privileged User Created Successfully");

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> getQualifiedApplicants() {
        log.info("Received a request to get approved students.");

        //Finding out the latest bursaries.
        //formApprovalRepository


        return null;
    }
}
