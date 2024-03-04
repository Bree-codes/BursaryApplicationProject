package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserRequestNotAuthorised;
import com.bree.springproject.onlinebursaryapplication.Entity.ApprovedFormsEntity;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.PrivilegedUserModel;
import com.bree.springproject.onlinebursaryapplication.models.ResponseModel;
import com.bree.springproject.onlinebursaryapplication.repository.FormApprovalRepository;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.*;

@Service
@Slf4j
public class AdminService {


    private final UserRegistrationRepository userRegistrationRepository;

    private final RegisterUserService registerUserService;


    private final CommunicationService communicationService;


    private final FormApprovalRepository formApprovalRepository;


    private final HandleChiefLogicService handleChiefLogicService;


    private final CreateFormService createFormService;


    private final PasswordEncoder encoder;

    @Autowired
    public AdminService(UserRegistrationRepository userRegistrationRepository, RegisterUserService registerUserService, CommunicationService communicationService, FormApprovalRepository formApprovalRepository, HandleChiefLogicService handleChiefLogicService, CreateFormService createFormService, PasswordEncoder encoder) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.registerUserService = registerUserService;
        this.communicationService = communicationService;
        this.formApprovalRepository = formApprovalRepository;
        this.handleChiefLogicService = handleChiefLogicService;
        this.createFormService = createFormService;
        this.encoder = encoder;
    }

    public ResponseEntity<ResponseModel> createUser(PrivilegedUserModel privilegedUserModel) {

        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();
        String defaultPassword = privilegedUserModel.getRole().toString();

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

            /*The phone number is a non-null field, so we are setting the
            random number as the default phone number to
            * be updated later by the created user.*/
            Random random = new Random();

            userRegistrationTable.setPhoneNumber(
                    String.valueOf(random.ints(1,9)));
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

        userRegistrationTable.setPassword(encoder.encode(userRegistrationTable.getPassword()));
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
        int latestMonthEncoding = handleChiefLogicService.latestFinder();

        String month = createFormService.decodeMonth(0,latestMonthEncoding);

        Map<String, String> approvedStudents = new HashMap<>();

        //bursary title.
        approvedStudents.put("Bursary Month", month+" 2024");

        /*Moving forward to get the approved students.*/
        List<Long> approvedFormsEntities =
                formApprovalRepository.findAllByBursaryMonth(String.valueOf(latestMonthEncoding));

        /*Moving forward to get approved students' names and phone numbers.*/
        for(Long userId : approvedFormsEntities)
        {
            UserRegistrationTable userRegistrationTable =
                    userRegistrationRepository.findById(userId).get();
            approvedStudents.put(
                    userRegistrationTable.getUsername(),
                    userRegistrationTable.getPhoneNumber());
        }

        return new ResponseEntity<>(approvedStudents, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> getQualifiedApplicantsByYearAndMonth(
            String bursaryYear, String bursaryMonth) {

        //creating the map to be return to teh admin
        Map<String, String> approvedStudent = new HashMap<>();

        /*Inserting the bursary title.*/
        approvedStudent.put("Bursary Month", bursaryMonth+" "+bursaryYear);

        /*First, we need to encode the provided month*/
        String encodedMonth =
                createFormService.encoder(bursaryMonth, Integer.parseInt(bursaryYear));

        List<Long> approvedFormsEntities =
                formApprovalRepository.findAllByBursaryMonth(encodedMonth);

        /*Moving forward to get approved students' names and phone numbers.*/
        for(Long userId : approvedFormsEntities)
        {
            UserRegistrationTable userRegistrationTable =
                    userRegistrationRepository.findById(userId).get();
            approvedStudent.put(
                    userRegistrationTable.getUsername(),
                    userRegistrationTable.getPhoneNumber());
        }



        return new ResponseEntity<>(approvedStudent, HttpStatus.OK);
    }
}
