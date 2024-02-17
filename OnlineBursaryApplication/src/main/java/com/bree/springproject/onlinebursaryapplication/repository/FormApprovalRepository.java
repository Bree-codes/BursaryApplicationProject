package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ApprovedFormsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface FormApprovalRepository extends CrudRepository<ApprovedFormsEntity, Long> {

    ApprovedFormsEntity findAllByUserIdAndBursaryMonth(Long userId, String bursaryMonth);


    @Query("SELECT t.userId FROM ApprovedFormsEntity t WHERE t.status = true")
    List<Long> findAllByBursaryMonth(String bursaryMonth);
}
