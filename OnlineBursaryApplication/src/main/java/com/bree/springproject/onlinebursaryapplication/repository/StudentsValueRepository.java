package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsValueRepository extends CrudRepository<StudentFormValues , Long> {

    StudentFormValues findByFieldIdAndUserId(Long fieldId, Long userId);


   /* @Query("SELECT new com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel" +
            "(a.fieldInputType, u.fieldValue, a.fieldId, a.section, a.fieldName, a.bursaryMonth) " +
            "FROM ApplicationFormCreateTable a, StudentFormValues u where " +
            "u.userId =: userId AND a.bursaryMonth =: bursaryMonth")
    List<StudentFormAndValuesModel> getFormAndValues(Long userId, String bursaryMonth);*/
}
