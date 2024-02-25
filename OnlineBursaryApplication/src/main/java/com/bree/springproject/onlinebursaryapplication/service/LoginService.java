package com.bree.springproject.onlinebursaryapplication.service;


import com.bree.springproject.onlinebursaryapplication.models.AuthenticationResponseModel;
import com.bree.springproject.onlinebursaryapplication.models.LoginModel;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Autowired
    public LoginService(UserRegistrationRepository userRegistrationRepository,
                        AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting user by username");
        return userRegistrationRepository.findByUsername(username);
    }

    public AuthenticationResponseModel login(LoginModel loginModel) {
        log.info("forwarded request for user login");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(),
                        loginModel.getPassword()
                )
        );

        log.info("Assigning a jwt token to user.");
        String token =
                jwtService.generateToken(loadUserByUsername(loginModel.getUsername()));

        log.info("preparing user response");
        AuthenticationResponseModel authenticationResponseModel =
                new AuthenticationResponseModel();

        authenticationResponseModel.setDate(new Date());
        authenticationResponseModel.setMessage("Login Successful");
        authenticationResponseModel.setHttpStatus(HttpStatus.OK);
        authenticationResponseModel.setToken(token);

        return authenticationResponseModel;
    }
}
