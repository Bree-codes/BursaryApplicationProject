package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
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
    UserRegistrationTable userRegistrationTable;

    public ResponseEntity<String> registrationValidation(UserRegistrationModel userRegistrationModel){

        userRegistrationTable.setUsername(userRegistrationModel.getUsername());
        userRegistrationTable.setPassword(userRegistrationModel.getPassword());
        userRegistrationTable.setEmail(userRegistrationModel.getEmail());

        //many computation.
        userRegistrationTable.setStatus(true);
        log.error("Validated the user");
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

}
