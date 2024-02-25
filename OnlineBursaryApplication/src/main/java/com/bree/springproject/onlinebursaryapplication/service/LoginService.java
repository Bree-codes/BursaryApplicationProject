package com.bree.springproject.onlinebursaryapplication.service;


import com.bree.springproject.onlinebursaryapplication.models.AuthenticationResponseModel;
import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public LoginService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting user by username");
        return userRegistrationRepository.findAllByUsername(username);
    }


}
