package com.bree.springproject.onlinebursaryapplication.service;


import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UnauthorisedRequestException;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ViewLogicService {

    @Autowired
    UserRegistrationRepository userRegistrationRepository;
    public ResponseEntity<List<Long>> getAvailableForms(Long viewerId) {
        log.info("Forwarded the request to find available forms");

        //check if the id belongs to a viewer.
         UserRegistrationTable userRegistrationTable =
                 userRegistrationRepository.findById(viewerId).get();


         if(!(userRegistrationTable.getRole().equalsIgnoreCase("view") ||
            userRegistrationTable.getRole().equalsIgnoreCase("admin")))
        {
            throw new UnauthorisedRequestException("The Id passed Does Not Belong to a view");
        }


        return null;
    }
}
