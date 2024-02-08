package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.StudentFormValues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsValueRepository extends CrudRepository<StudentFormValues , Long> {

    StudentFormValues findByFieldIdAndUserId(Long fieldId, Long userId);
}
