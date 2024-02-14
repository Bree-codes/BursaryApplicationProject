package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.FieldValuesAlreadyExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidFieldIdProvidedException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.NoFormAvailableException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserFieldDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import com.bree.springproject.onlinebursaryapplication.repository.FormCreateRepository;
import com.bree.springproject.onlinebursaryapplication.repository.StudentsValueRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<String> updateValues(String fieldValue, Long fieldId, Long userId) {
        log.info("Forwarded the request to save the Student values.");

        //check if the field exists.
        if(studentsValueRepository.findByFieldIdAndUserId(fieldId, userId) == null)
        {
            throw new UserFieldDoesNotExistException("The Field Id Or The User Id provided is Invalid");
        }

        //forwarding the use input for saving
        studentsValueRepository.updateFieldValueByFieldIdAndUserId(fieldValue, fieldId, userId);

        return new ResponseEntity<>("Values updated", HttpStatus.OK);
    }

    public ResponseEntity<String> saveFormValues
            (Long userId, Map<Long, String> fieldIdAndValue) {
        log.info("Forwarded a request to save the submitted form values.");

        List<StudentFormValues> studentFormValuesList = new ArrayList<>();

        //set of all the fields in the form
        Set<Long> fieldIds = fieldIdAndValue.keySet();


        log.info("Preparing the values for saving.");
        for (Long fieldId : fieldIds) {

            //check whether the field already exists.
            if (studentsValueRepository.findByFieldIdAndUserId(fieldId, userId) != null)
                throw new FieldValuesAlreadyExistException("Attempt to store duplicate values is not allowed");

            StudentFormValues studentFormValues = new StudentFormValues();

            studentFormValues.setFieldId(fieldId);
            studentFormValues.setFieldValue(fieldIdAndValue.get(fieldId));
            studentFormValues.setUserId(userId);

            ApplicationFormCreateTable applicationFormCreateTable =
                    formCreateRepository.findByFieldId(fieldId);
            if (applicationFormCreateTable == null) {
                throw new InvalidFieldIdProvidedException("The FieldId Entered is invalid");
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
        List<StudentFormAndValuesModel> formAndValues;
        StudentFormAndValuesModel studentFormAndValuesModel;
        List<List<StudentFormAndValuesModel>> lists = new ArrayList<>();

        if (form == null) {
            throw new NoFormAvailableException("No Form Available For Application");
        }

        String latestFormMonth = form.get(0).get(0).getBursaryMonth();

        latestFormMonth = createFormService.encoder(latestFormMonth, 0);

        log.info("getting the form values.");
        List<StudentFormValues> studentFormValuesList = studentsValueRepository.
                findAllByUserIdAndBursaryMonthOrderByBursaryMonth(userId, latestFormMonth);

        /*formAndValues = studentsValueRepository.getFormAndValues(userId, latestFormMonth);*/


        //this variable will help identify unbound fields.
        boolean bindingStatus = false;

        //this is an alternative to the jpa query.
        for (List<ApplicationFormCreateTable> table : form) {
            formAndValues = new ArrayList<>();

            for (ApplicationFormCreateTable formRow : table) {
                studentFormAndValuesModel = new StudentFormAndValuesModel();

                studentFormAndValuesModel.setFieldInputType(formRow.getFieldInputType());
                studentFormAndValuesModel.setFieldName(formRow.getFieldName());
                studentFormAndValuesModel.setSection(formRow.getSection());
                studentFormAndValuesModel.setBursaryMonth(formRow.getBursaryMonth());
                studentFormAndValuesModel.setFieldId(formRow.getFieldId());

                //values finder for fields
                for (StudentFormValues valuesRow : studentFormValuesList) {
                    if (valuesRow.getFieldId().equals(formRow.getFieldId())) {
                        if (valuesRow.getFieldValue().isBlank()) {

                            studentFormAndValuesModel.setFieldValue("");
                            studentFormAndValuesModel.setValueId(valuesRow.getValueId());
                        }else {
                            studentFormAndValuesModel.setFieldValue(valuesRow.getFieldValue());
                            studentFormAndValuesModel.setValueId(valuesRow.getValueId());
                        }
                            formAndValues.add(studentFormAndValuesModel);
                            bindingStatus = true;
                            break;
                    }
                }
                if (!bindingStatus) {
                    studentFormAndValuesModel.setFieldValue("");
                    studentFormAndValuesModel.setValueId(0L);
                    formAndValues.add(studentFormAndValuesModel);
                }


            }
            lists.add(formAndValues);
        }
            return new ResponseEntity<>(lists, HttpStatus.OK);
    }
}
