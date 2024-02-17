package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.DuplicateFormFieldException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.FormNotFoundException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidUpdateException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.NoFormAvailableException;
import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.models.Months;
import com.bree.springproject.onlinebursaryapplication.repository.FormCreateRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
public class CreateFormService {

    @Autowired
    FormCreateRepository formCreateRepository;

    public ResponseEntity<String> createSectionA(Map<String,
            String> sectionA, String month, Long userId, String section) {

        log.info("Forwarded the request to create the form");


        //encoding the month
        String monthFieldValue = encoder(month, 0);

        log.info("Proceeding with insertion.");
        List<String> fields = new ArrayList<>(sectionA.keySet());
        List<ApplicationFormCreateTable> sectionAColumns = new ArrayList<>();

        log.info("Checking for any duplicates.");
        for(String field : fields)
        {
            ApplicationFormCreateTable sectionAColumn = new ApplicationFormCreateTable();

            sectionAColumn.setUserId(userId);
            sectionAColumn.setBursaryMonth(monthFieldValue);
            sectionAColumn.setFieldName(field);
            sectionAColumn.setFieldInputType(sectionA.get(field));
            sectionAColumn.setSection(section);

            if(formCreateRepository.
                    findByBursaryMonthAndFieldNameAndSection(monthFieldValue, field, section) != null)
            {
                throw new DuplicateFormFieldException("Duplicate Fields In The Same Section Id Not Allowed.");
            }

            sectionAColumns.add(sectionAColumn);
        }

        //batch update.
        formCreateRepository.saveAll(sectionAColumns);

        return new ResponseEntity<>("Saved Successfully", HttpStatus.CREATED);
    }

    public String encoder(String month, int year)
    {
        month = month.toLowerCase();

        //get the numerical value of the month provided.
        int monthValue = Months.valueOf(month).ordinal();
        //getting the year.
        int currentYear = year;

        if(currentYear == 0) currentYear = Year.now().getValue();

        monthValue = monthValue + (12 * (currentYear - 2024));

        //getting the total value of the month-field for the form.
        return String.valueOf((monthValue + currentYear));
    }




    /*This method is responsible for updating the form */
    public ResponseEntity<String> updateForm(ApplicationFormCreateTable updatedSection) {

        log.info("Forwarded the request to update the form");

        //we are encoding the month name back to our numeric encoding.
        String monthYear = encoder(updatedSection.getBursaryMonth(), 0);

        //check whether the update was invalid.
        if(formCreateRepository.findAllByBursaryMonthOrderBySectionAsc(monthYear) == null
                || formCreateRepository.findAllByBursaryMonthOrderBySectionAsc(monthYear).isEmpty())
        {
            throw new InvalidUpdateException("The Bursary Month Should Never Be changed after creation, " +
                    "Alternatively try creating the form.");
        }

        //encoding the update form
        updatedSection.setBursaryMonth(monthYear);

        //saving back the updated section
        formCreateRepository.save(updatedSection);

        log.info("Updated successfully");
        return new ResponseEntity<>("Update successful", HttpStatus.OK);
    }


    public ResponseEntity<List<List<ApplicationFormCreateTable>>> getForm() {

        log.info("Forwarded request to get the form");

        int year = Year.now().getValue();

        int month = LocalDate.now().getMonthValue();

        int searchValue = month + year+12;


        List<ApplicationFormCreateTable> applicationForm;


        //getting the complete form.
        do{
            applicationForm = formCreateRepository.
                    findAllByBursaryMonthOrderBySectionAsc(String.valueOf(searchValue));

            searchValue = searchValue - 1;

            if(searchValue <= 2023){
                throw new NoFormAvailableException("There isn't any form available.");
            }

        }while(applicationForm.isEmpty());


        //where we need to break down the form into sections.

        return new ResponseEntity<>( sortingForm(applicationForm, 0), HttpStatus.OK);
    }

    private List<List<ApplicationFormCreateTable>> sortingForm(
            List<ApplicationFormCreateTable> applicationForm, int year) {


        //list to hold the sorted form.
        List<List<ApplicationFormCreateTable>> sortedForm = new ArrayList<>();
        List<ApplicationFormCreateTable> section = new ArrayList<>();
        ApplicationFormCreateTable lastRow = applicationForm.get(applicationForm.size()-1);


        /*In the for loop below, I am grouping form in sections as provided from the database.
        * We will also decode the month from the given integer*/
        String previousSection = null;

        for(ApplicationFormCreateTable row : applicationForm) {


            /*Here we need to decode the month name from the given number
            This method may be reused when coding the functionality where the
            user enters a specific month when they want to get the form for, so will check if the
            year provided is 0*/
            log.info("decoding the months");
            int currentYear = year;
            

            //handle the default get method
            if(year == 0) currentYear = Year.now().getValue();


            int month = Integer.parseInt(row.getBursaryMonth()) - currentYear - (12 * (currentYear - 2024));

            String formMonth = String.valueOf(Months.values()[month]);

            row.setBursaryMonth(formMonth);


            log.info("Moving to grouping of the form");

            String currentSection = row.getSection();

            if (currentSection.equals(previousSection)) section.add(row);
            else {
                sortedForm.add(section);
                section = new ArrayList<>();
                section.add(row);
                previousSection = currentSection;
            }

            if(row == lastRow) sortedForm.add(section);
        }

        sortedForm.remove(0);
        log.info("Decoding and Grouping of the form done.");
        return sortedForm;
    }

    public ResponseEntity<List<List<ApplicationFormCreateTable>>> getForm(String month, String year) {

        log.info("Forwarding for encoding");
        //the number format exception is handled
        String monthValue = encoder(month, Integer.parseInt(year));

        log.info("Getting the data");
        List<ApplicationFormCreateTable> getList =
                formCreateRepository.findAllByBursaryMonthOrderBySectionAsc(monthValue);


        if(getList == null || getList.isEmpty())
            throw new FormNotFoundException("The Requested Form Was Not Found");

        log.info("Forwarding form for sorting");
        List<List<ApplicationFormCreateTable>> form = sortingForm(getList, Integer.parseInt(year));

        return new ResponseEntity<>(form,HttpStatus.OK);
    }
}

