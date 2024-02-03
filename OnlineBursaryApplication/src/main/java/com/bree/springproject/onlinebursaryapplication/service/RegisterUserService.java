package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidPhoneNumberException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.WeakPasswordException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import com.bree.springproject.onlinebursaryapplication.userDTO.RegisterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserService {
    /*
    Encrypt the passwords
    Strength of the password
    Verify(Emailing the user)and Validate email
    * */
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;



    public ResponseEntity<String> registrationValidation(RegisterUserDTO registerUserDTO){
        log.info("Forwarded the request to register a new user.");

        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();

        //check validity of the phone number entered.
        if(!checkValidityOfPhoneNumber(registerUserDTO.getUserPhoneNumber()))
        {
            throw new InvalidPhoneNumberException("The Phone Number Enter Is Invalid");
        }


        //checking if the user exist
        if(userRegistrationRepository.findByPhoneNumber(registerUserDTO.getUserPhoneNumber()) != null)
        {
            throw new UserExistException("The Phone Number is Already Taken.");
        }

        //check if the password is strong enough.
        if(!checkPasswordStrength(registerUserDTO.getUserPassword()))
        {
            throw new WeakPasswordException("The Password Entered Does Not Meet The Required Criteria");
        }

        //check if the user has an email
        if(registerUserDTO.getUserEmail() == null)
        {
            //Here we should send a message to the phone number.
        }
        else {
            //Here we should email users to verify their emails.
        }

        log.info("Moving forward to insert the user.");
        //filling the new user to the entity for inserting.
        userRegistrationTable.setUsername(registerUserDTO.getUserName());
        userRegistrationTable.setEmail(registerUserDTO.getUserEmail());
        userRegistrationTable.setPhoneNumber(registerUserDTO.getUserPhoneNumber());
        userRegistrationTable.setPassword(registerUserDTO.getUserPassword());


        //saving the user to the database.
        userRegistrationRepository.save(userRegistrationTable);


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

    public Boolean checkPasswordStrength(String password)
    {
        Pattern passwordPattern = Pattern.
                compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z](?=.*[`~!@#$%^&*)(_+=}{:'?><,./|;]))");

        //check if the password matches the specifications.
        Matcher matchPassword = passwordPattern.matcher(password);

        return matchPassword.matches();
    }

    public Boolean checkValidityOfPhoneNumber(String phoneNumber)
    {

        //check where the patter has digits only.
       Pattern phoneNumberPattern = Pattern.compile("(^\\d+$)");

       Matcher testResults = phoneNumberPattern.matcher(phoneNumber);

        return testResults.matches();
    }

}
