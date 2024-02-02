package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserService {
    /*
    Encrypt the passwords
    Strength of the password
    Verify(Emailing the user)and Validate correct email
    * */
    @Autowired
    UserRegistrationRepository userRegistrationRepository;


    public ResponseEntity<String> registrationValidation(UserRegistrationTable userRegistrationModel){


        userRegistrationRepository.save(userRegistrationModel);


        log.error("Validated the user");
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updatePassword(String userPassword, String userEmail) {
        log.info("Forwarding the password update.");

        //validate the password strength here.

        //update the password
            //first we get the user by email.
        UserRegistrationTable userRegistrationTable = userRegistrationRepository.findByEmail(userEmail);

        //update the password
        userRegistrationTable.setPassword(userPassword);

        //merge back the user.
        userRegistrationRepository.save(userRegistrationTable);


        return new ResponseEntity<>("Password update successful", HttpStatus.OK);
    }

    public ResponseEntity<String> changePassword(String userEmail) {
        log.info("Forwarded the forgot password request");

        //check if the email exist in the database.
        if(userRegistrationRepository.findByEmail(userEmail) == null)
        {
            throw new UserDoesNotExistException("The Email Entered Does Not Much Any User.");
        }

        //will send the email to this user to change their password.
        //an error may occur at this point , so we should remember to handle the exceptions.



        //after the email is sent we return.
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

}
