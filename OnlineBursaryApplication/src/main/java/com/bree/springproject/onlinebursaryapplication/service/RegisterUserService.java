package com.bree.springproject.onlinebursaryapplication.service;

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

    public ResponseEntity<String> updatePassword(String userPassword) {


        return new ResponseEntity<>("Password update successful", HttpStatus.OK);
    }

    public void changePassword(String userName) {
        log.info("Forwarded the forgot password request");


    }
}
