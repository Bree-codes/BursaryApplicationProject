package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidPhoneNumberException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.WeakPasswordException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import com.bree.springproject.onlinebursaryapplication.userDTO.RegisterUserDTO;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
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


    @Autowired
    CommunicationService communicationService;

    public ResponseEntity<String> registrationValidation(RegisterUserDTO registerUserDTO) throws MessagingException, UnknownHostException {
        log.info("Forwarded the request to register a new user.");
        //instance of a new user.
        UserRegistrationTable userRegistrationTable = new UserRegistrationTable();

        log.info("validating user phone number");
        //check the validity of the phone number entered.
        if(!checkValidityOfPhoneNumber(registerUserDTO.getUserPhoneNumber()))
        {
            log.error("Invalid phone number entered.");
            throw new InvalidPhoneNumberException("The Phone Number Enter Is Invalid");
        }

        log.info("Check user existence by phone number.");
        //checking if the user exists
        if(userRegistrationRepository.findByPhoneNumber(registerUserDTO.getUserPhoneNumber()) != null)
        {
            log.error("User exist.");
            throw new UserExistException("The Phone Number is Already Taken.");
        }

        log.info("Checking password strength");
        //check if the password is strong enough.
        if(!checkPasswordStrength(registerUserDTO.getUserPassword()))
        {
            log.error("Weak password entered");
            throw new WeakPasswordException("The Password Entered Does Not Meet The Required Criteria");
        }

        /*If the user has both emails and phone number entered, we prefer using email verification
        * to verify their account to reduce on the cost.
        * Else if the user does not have the email, we fall back to using the phone number for verifications
        * as it is a mandatory field as opposed to the email field*/

        //check if the user has an email
        if(registerUserDTO.getUserEmail() == null)
        {
            //Here we should send a message to the phone number.


        }
        else {
            //Here we should email users to verify their emails.
            if(userRegistrationRepository.findByEmail(registerUserDTO.getUserEmail()) != null)
            {
                throw new UserExistException("The Email You Entered is Already Taken");
            }

            log.info("Sending the verification email");
            //send the email for verifications.
            communicationService.sendVerificationEmails("https://github.com/Red-stevo",
                    registerUserDTO.getUserEmail());
        }

        log.info("Moving forward to insert the user.");
        //filling the new user to the entity for inserting.
        userRegistrationTable.setUsername(registerUserDTO.getUserName());
        userRegistrationTable.setEmail(registerUserDTO.getUserEmail());
        userRegistrationTable.setPhoneNumber(registerUserDTO.getUserPhoneNumber());
        userRegistrationTable.setPassword(registerUserDTO.getUserPassword());


        //saving the user to the database.
        userRegistrationRepository.save(userRegistrationTable);

        log.info("New User Added successfully");
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updatePassword(String userPassword, String userEmail) {
        log.info("Forwarding the password update.");

        //validate the password strength here.
        if(!checkPasswordStrength(userPassword))
        {
            throw new WeakPasswordException("The Password Entered Does Not Meet The Required Criteria");
        }

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

        //check if the email exists in the database.
        if(userRegistrationRepository.findByEmail(userEmail) == null)
        {
            throw new UserDoesNotExistException("The Email Entered Does Not Much Any User.");
        }

        //will send the email to this user to change their password.
        //an error may occur at this point, so we should remember to handle the exceptions.



        //after the email is sent, we return.
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    public Boolean checkPasswordStrength(String password)
    {

        log.info("Check password pattern");
        Pattern passwordPattern = Pattern.
                compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+.,?=])\\S{8,}(?!.*(\\w)\\1{2,})$");

        //check if the password matches the specifications.
        Matcher matchPassword = passwordPattern.matcher(password);
        System.out.println(matchPassword.matches());
        return matchPassword.matches();
    }

    public Boolean checkValidityOfPhoneNumber(String phoneNumber)
    {

        //check where the patter has digits only.
       Pattern phoneNumberPattern = Pattern.compile("(^\\d+$)");

       Matcher testResults = phoneNumberPattern.matcher(phoneNumber);

        return testResults.matches();
    }


    public ResponseEntity<String> verifyEmail(Boolean verify, String userEmail) {

        log.info("Verifying user Email.");

        UserRegistrationTable userRegistrationTable = userRegistrationRepository.findByEmail(userEmail);

        //confirming the email exists in the database.
        if(userRegistrationTable == null) {
            log.error("Invalid verification email.");
            throw new UserDoesNotExistException("User Verification failed, User does Not exist");
        }
        userRegistrationTable.setStatus(verify);

        //saving back the user.
        userRegistrationRepository.save(userRegistrationTable);

        return new ResponseEntity<>("verification passed.", HttpStatus.OK);
    }
}
