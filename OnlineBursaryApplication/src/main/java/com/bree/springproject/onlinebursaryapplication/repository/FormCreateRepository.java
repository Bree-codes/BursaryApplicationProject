package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ApplicationFormCreateTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormCreateRepository extends JpaRepository<ApplicationFormCreateTable, Long> {

    List<ApplicationFormCreateTable> findAllByBursaryMonthOrderBySectionAsc(String bursaryMonth);

    ApplicationFormCreateTable findByFieldId(Long fieldId);

    ApplicationFormCreateTable findByBursaryMonthAndFieldNameAndSection(String month, String fieldName, String section);


}
