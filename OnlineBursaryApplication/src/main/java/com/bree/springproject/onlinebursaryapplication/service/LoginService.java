package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    public ResponseEntity<LoginModel> loginUser(String username, String password) {
        LoginModel loginModel = userRegistrationRepository.findUserDetails(username,password);
        return new ResponseEntity<>(loginModel,HttpStatus.OK);
    }
}
