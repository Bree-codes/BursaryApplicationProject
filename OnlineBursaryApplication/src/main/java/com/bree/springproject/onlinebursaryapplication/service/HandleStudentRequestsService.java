package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.FieldValuesAlreadyExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidFieldIdProvidedException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.NoFormAvailableException;
import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.models.Months;
import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import com.bree.springproject.onlinebursaryapplication.repository.FormCreateRepository;
import com.bree.springproject.onlinebursaryapplication.repository.StudentsValueRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@Data
public class HandleStudentRequestsService {

    @Autowired
    StudentsValueRepository studentsValueRepository;

    @Autowired
    CreateFormService createFormService;

    @Autowired
    FormCreateRepository formCreateRepository;

    public ResponseEntity<String> updateValues(StudentFormValues formValues) {
        log.info("Forwarded the request to save the Student values.");

        //forwarding the use input for saving
        studentsValueRepository.save(formValues);

        return new ResponseEntity<>("Values saved", HttpStatus.OK);
    }

    public ResponseEntity<String> saveFormValues
            (Long userId, Map<Long, String> fieldIdAndValue) {
        log.info("Forwarded a request to save the submitted form values.");

        List<StudentFormValues> studentFormValuesList = new ArrayList<>();

        //set of all the fields in the form
        Set<Long> fieldIds = fieldIdAndValue.keySet();



        log.info("Preparing the values for saving.");
        for(Long fieldId : fieldIds)
        {

            //check whether the field already exists.
            if(studentsValueRepository.findByFieldIdAndUserId(fieldId, userId) != null)
                throw new FieldValuesAlreadyExistException("Attempt to store duplicate values is not allowed");

            StudentFormValues studentFormValues = new StudentFormValues();

            studentFormValues.setFieldId(fieldId);
            studentFormValues.setFieldValue(fieldIdAndValue.get(fieldId));
            studentFormValues.setUserId(userId);

            ApplicationFormCreateTable applicationFormCreateTable =
                    formCreateRepository.findByFieldId(fieldId);
            if(applicationFormCreateTable == null)
            {
                throw  new InvalidFieldIdProvidedException("The FieldId Entered is invalid");
            }

            studentFormValues.setBursaryMonth(applicationFormCreateTable.getBursaryMonth());

            studentFormValuesList.add(studentFormValues);
        }

        log.info("Moving forward to saving the values.");
        studentsValueRepository.saveAll(studentFormValuesList);

        return new ResponseEntity<>("Form Values Saved Successfully", HttpStatus.OK);
    }

    public ResponseEntity<List<List<StudentFormAndValuesModel>>> getBindLatestFormAndValues(Long userId) {

        List<List<ApplicationFormCreateTable>> form = createFormService.getForm().getBody();
        List<StudentFormAndValuesModel> formAndValues = new ArrayList<>();

        if(form == null)
        {
            throw new NoFormAvailableException("No Form Available For Application");
        }

        String latestFormMonth = form.get(0).get(0).getBursaryMonth();

        latestFormMonth = createFormService.encoder(latestFormMonth, 0);

        log.info("getting the form values.");
        List<StudentFormValues> studentFormValuesList = studentsValueRepository.
                findAllByUserIdAndBursaryMonthOrderByBursaryMonth(userId, latestFormMonth);

        formAndValues = studentsValueRepository.getFormAndValues(userId, latestFormMonth);


        //this is an alternative to the jpa query.
       /* for(StudentFormValues valuesRow : studentFormValuesList)
        {
            StudentFormAndValuesModel studentFormAndValuesModel = new StudentFormAndValuesModel();

            for(List<ApplicationFormCreateTable> table : form)
            {
                for(ApplicationFormCreateTable formRow : table)
                {
                    if(valuesRow.getFieldId().equals(formRow.getFieldId()))
                    {
                        studentFormAndValuesModel.setFieldInputType(formRow.getFieldInputType());
                        studentFormAndValuesModel.setFieldName(formRow.getFieldName());
                        studentFormAndValuesModel.setSection(formRow.getSection());
                        studentFormAndValuesModel.setFieldValue(valuesRow.getFieldValue());
                        studentFormAndValuesModel.setBursaryMonth(valuesRow.getBursaryMonth());
                        studentFormAndValuesModel.setFieldId(valuesRow.getFieldId());

                        //removing the already mapped items
                        //studentFormValuesList.remove(valuesRow);
                        //table.remove(formRow);

                        break;
                    }
                }
                //pushing the values
                formAndValues.add(studentFormAndValuesModel);
                break;
            }

        }*/
       return new ResponseEntity<>(sortingForm(formAndValues, 0), HttpStatus.OK);
    }


    private List<List<StudentFormAndValuesModel>> sortingForm(
            List<StudentFormAndValuesModel> applicationForm, int year) {

        log.info("bing the grouping the values by section");

        //list to hold the sorted form.
        List<List<StudentFormAndValuesModel>> sortedForm = new ArrayList<>();
        List<StudentFormAndValuesModel> section = new ArrayList<>();
        StudentFormAndValuesModel lastRow = applicationForm.get(applicationForm.size()-1);


        /*In the for loop below, I am grouping form in sections as provided from the database.
         * We will also decode the month from the given integer*/
        String previousSection = null;

        for(StudentFormAndValuesModel row : applicationForm) {

            /*Here we need to decode the month name from the given number
            This method may be reused when coding the functionality where the
            user enters a specific month when they want to get the form for, so will check if the
            year provided is 0*/
            log.info("decoding the months");
            int currentYear = year;


            //handle the default get method
            if(year == 0) currentYear = Year.now().getValue();


            int month = Integer.parseInt(row.getBursaryMonth()) - currentYear;

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
}
