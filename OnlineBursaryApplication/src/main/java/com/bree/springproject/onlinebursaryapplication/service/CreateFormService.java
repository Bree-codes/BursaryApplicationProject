package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import com.bree.springproject.onlinebursaryapplication.models.Months;
import com.bree.springproject.onlinebursaryapplication.repository.FormCreateRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Service
@Slf4j
@Data
public class CreateFormService {

    @Autowired
    FormCreateRepository formCreateRepository;

    public ResponseEntity<String> createSectionA(Map<String,
            String> sectionA, String month, Long userId, String section) {

        log.info("Forwarded the request to create the form");


        //validate the month format.
        log.info("Validating the month format.");

        month = month.toLowerCase();

        //get the numerical value of the month provided.
        int monthValue = Months.valueOf(month).ordinal();
       //getting the year.
        int currentYear = Year.now().getValue();

        //getting the total value of the month-field for the form.
        String monthFieldValue = String.valueOf((monthValue + currentYear));

        log.info("Proceeding with insertion.");
        List<String> fields = new ArrayList<>(sectionA.keySet());
        List<ApplicationFormCreateTable> sectionAColumns = new ArrayList<>();

        for(String field : fields)
        {
            ApplicationFormCreateTable sectionAColumn = new ApplicationFormCreateTable();

            sectionAColumn.setUserId(userId);
            sectionAColumn.setBursaryMonth(monthFieldValue);
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

        int year = Year.now().getValue();

        int month = LocalDate.now().getMonthValue();

        int searchValue = month + year;

        List<ApplicationFormCreateTable> applicationForm;


        //getting the complete form.
        do{
            applicationForm = formCreateRepository.findAllByBursaryMonth(String.valueOf(searchValue));
            searchValue = searchValue - 1;

        }while(applicationForm.isEmpty());


        //where we need to break down the form into sections.

        return new ResponseEntity<>( sortingForm(applicationForm), HttpStatus.OK);
    }

    private List<List<ApplicationFormCreateTable>> sortingForm(
            List<ApplicationFormCreateTable> applicationForm) {

        //list to hold the sorted form.
        List<List<ApplicationFormCreateTable>> sortedForm = new ArrayList<>();
        List<ApplicationFormCreateTable> section = new ArrayList<>();
        log.info("Breaking down the form into sections");

        String currentSection = applicationForm.get(1).getSection();
        String previousSection = null;

        for(ApplicationFormCreateTable row : applicationForm) {
            if (!currentSection.equals(previousSection)) {
                sortedForm.add(section);
                section = new ArrayList<>();
                section.add(row);
                previousSection = currentSection;
            } else {
                section.add(row);
            }
        }

        log.info("Grouping of the form done.");
        return sortedForm;
    }
}

