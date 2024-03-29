package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentsValueRepository extends CrudRepository<StudentFormValues , Long> {

    StudentFormValues findByFieldIdAndUserId(Long fieldId, Long userId);

    List<StudentFormValues> findAllByUserIdAndBursaryMonthOrderByBursaryMonth(Long userId, String bursaryMonth);

    @Modifying
    @Transactional
    @Query("UPDATE StudentFormValues SET fieldValue = :fieldValue WHERE fieldId = :fieldId AND userId = :userId")
    void updateFieldValueByFieldIdAndUserId(String fieldValue, Long fieldId, Long userId);

   /* Boolean updateByFieldId(Long fieldId, StudentFormValues studentFormValues);*/

/*
    @Query("SELECT new com.bree.springproject.onlinebursaryapplication.models.StudentFormAndValuesModel" +
            "(a.fieldInputType, u.fieldValue, a.fieldId, a.section, a.fieldName, a.bursaryMonth) " +
            "FROM ApplicationFormCreateTable a, StudentFormValues u where " +
            "u.userId = ?1 AND a.bursaryMonth = ?2 AND u.fieldId=a.fieldId")
    List<StudentFormAndValuesModel> getFormAndValues(Long userId, String bursaryMonth);
*/

}
