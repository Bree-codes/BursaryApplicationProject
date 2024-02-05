package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.repository.FormCreateRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@Data
public class CreateFormService {

    @Autowired
    FormCreateRepository formCreateRepository;

    public ResponseEntity<String> createSectionA(Map<String,
            String> sectionA, String month, Long userId, String section) {

        List<String> fields = new ArrayList<>(sectionA.keySet());
        List<ApplicationFormCreateTable> sectionAColumns = new ArrayList<>();

        for(String field : fields)
        {
            ApplicationFormCreateTable sectionAColumn = new ApplicationFormCreateTable();

            sectionAColumn.setUserId(userId);
            sectionAColumn.setBursaryMonth(month);
            sectionAColumn.setField(field);
            sectionAColumn.setType(sectionA.get(field));
            sectionAColumn.setSection(section);
            sectionAColumns.add(sectionAColumn);
        }

        //batch update.
        formCreateRepository.saveAll(sectionAColumns);

        return new ResponseEntity<>("Saved Successfully", HttpStatus.CREATED);
    }

    /*This method is responsible for updating the form */
    public ResponseEntity<String> updateForm(List<ApplicationFormCreateTable> updatedSection) {

        log.info("Forwarded the request to update the form");

        //here we batch update the form.
        formCreateRepository.saveAll(updatedSection);

        log.info("Updated successfully");
        return new ResponseEntity<>("Update successful", HttpStatus.OK);
    }


    public ResponseEntity<List<List<ApplicationFormCreateTable>>> getForm() {
        log.info("Forwarded request to get the form");

        //we are going to load the latest form.
        Date date = new Date();

        String month = String.valueOf(date.getMonth());
        String year = String.valueOf(date.getYear());

        //field to be searched for.
        String monthField = month+"_"+year;

        //get the data.
        List<ApplicationFormCreateTable> form = formCreateRepository.findAllByBursaryMonth(monthField);


        //process the form to break it down into sections.

        return null;
    }
}

