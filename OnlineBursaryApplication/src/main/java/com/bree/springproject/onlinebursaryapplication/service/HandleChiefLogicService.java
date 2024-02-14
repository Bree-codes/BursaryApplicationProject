package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.*;
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
import java.util.List;

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

    public ResponseEntity<List<ChiefDataEntity>> getForms(Long chiefId) {

        log.info("forwarded the request to get the forms.");

        //validate the request.
        String userRole =
                userRegistrationRepository.findById(chiefId).get().getRole();

        if(!userRole.equalsIgnoreCase("chief"))
        {
            throw new UnauthorisedRequestException("User Id Entered Does Not Belong To A chief");
        }

        //find out the latest bursary month
        List<String> bursaryMonthsEncoded = chiefRequestRepository.findDistinctBursaryMonth();


        int latest = 2023;

        for (String monthEncoded : bursaryMonthsEncoded)
        {
            int bursaryMonthValue = Integer.parseInt(monthEncoded);

           if(bursaryMonthValue > latest)
           {
               latest = bursaryMonthValue;
           }
        }

        //find the chief username.
        String chiefName = userRegistrationRepository.findUsernameByUserId(chiefId);

        log.info("fetching the froms.");
        //moving forward to get the form.
        List<ChiefDataEntity> sentForms = chiefRequestRepository.findAllByChiefUserNameAndBursaryMonth(
                chiefName,
                String.valueOf(latest),
                null
        );

        return new ResponseEntity<>(sentForms, HttpStatus.OK);
    }

    public ResponseEntity<String> consent(Long userId, Boolean status, String bursaryMonth) {
        log.info("forwarded the request for consenting");

        if(!chiefRequestRepository.existsById(userId)){
            throw new UserDoesNotExistException("The UserId Entered Does Not Exists");
        }

        //encoding the bursary month.
        createFormService.encoder(bursaryMonth, 0);


        chiefRequestRepository.updateStatusByUserId(status, userId, bursaryMonth);

        return new ResponseEntity<>("Consenting Successful", HttpStatus.OK);
    }
}
