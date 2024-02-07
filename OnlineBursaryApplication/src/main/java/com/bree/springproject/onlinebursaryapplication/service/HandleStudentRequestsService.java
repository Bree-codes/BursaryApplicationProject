package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.repository.StudentsValueRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class HandleStudentRequestsService {

    StudentsValueRepository studentsValueRepository;

    public ResponseEntity<String> saveValues(StudentFormValues formValues) {
        log.info("Forwarded the request to save the Student values.");

        //forwarding the use input for saving
        studentsValueRepository.save(formValues);

        return new ResponseEntity<>("Values saved", HttpStatus.OK);
    }
}
