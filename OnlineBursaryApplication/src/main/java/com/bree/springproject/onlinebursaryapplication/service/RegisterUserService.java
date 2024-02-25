package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidPhoneNumberException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.WeakPasswordException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.AuthenticationResponseModel;
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
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class RegisterUserService {
    /*
    Encrypt the passwords
    Strength of the password
    Verify(Emailing the user)and Validate email
    return a jwt token
    * */
    private final UserRegistrationRepository userRegistrationRepository;

    private final CommunicationService communicationService;

    private final  JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterUserService(
            UserRegistrationRepository userRegistrationRepository,
            CommunicationService communicationService,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.communicationService = communicationService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<AuthenticationResponseModel>
    registrationValidation(RegisterUserDTO registerUserDTO)
            throws MessagingException, UnknownHostException {
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
        if(registerUserDTO.getUserPhoneNumber() == null)
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

        /*Encryption the password before storage*/
        userRegistrationTable.setPassword(passwordEncoder.encode(
                registerUserDTO.getUserPassword()));

        //saving the user to the database.
        userRegistrationRepository.save(userRegistrationTable);


        /*Generating the jwt token*/
        String jwtToken = jwtService.generateToken(userRegistrationTable);

        AuthenticationResponseModel authenticationResponseModel = new AuthenticationResponseModel();
        authenticationResponseModel.setToken(jwtToken);
        authenticationResponseModel.setDate(new Date());
        authenticationResponseModel.setHttpStatus(HttpStatus.CREATED);
        authenticationResponseModel.setMessage("User created successfully");

        log.info("New User Added successfully");
        return new ResponseEntity<>(authenticationResponseModel, HttpStatus.CREATED);
    }

    public ResponseEntity<String> updatePassword(String userPassword, String userEmailOrPhoneNumber) {

        UserRegistrationTable userRegistrationTable = null;

        log.info("Forwarding the password update.");

        //validate the password strength here.
        if(!checkPasswordStrength(userPassword))
        {
            throw new WeakPasswordException("The Password Entered Does Not Meet The Required Criteria");
        }

        //check whether it is an email or a phone number.
        if(!checkValidityOfPhoneNumber(userEmailOrPhoneNumber))
        {
            userRegistrationTable = userRegistrationRepository.findByEmail(userEmailOrPhoneNumber);
        }
        else {
            userRegistrationTable = userRegistrationRepository.findByPhoneNumber(userEmailOrPhoneNumber);
        }

        if(userRegistrationTable == null)
        {
            throw new UserDoesNotExistException("Invalid password update attempt, User does not exist");
        }

        //update the password
        userRegistrationTable.setPassword(userPassword);

        //merge back the user.
        userRegistrationRepository.save(userRegistrationTable);
        log.info("Password updated successfully");

        return new ResponseEntity<>("Password update successful", HttpStatus.OK);
    }

    public ResponseEntity<String> changePassword(String userEmailOrPassword) throws MessagingException {
        log.info("Forwarded the forgot password request");

        //check whether it is an email or a password.
        if(!checkValidityOfPhoneNumber(userEmailOrPassword))
        {
            String url = "";
            //the string is an email
            communicationService.sendChangePasswordEmail(userEmailOrPassword, url);

            log.info("Email sent successfully");

            return new ResponseEntity<>("Check your Email.",HttpStatus.OK);
        }

        //here we send the message to the phone number.


        //after the email is sent, we return.
        return new ResponseEntity<>("Check Your messages ", HttpStatus.OK);
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
