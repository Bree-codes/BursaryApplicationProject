package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ApprovedFormsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormApprovalRepository extends CrudRepository<ApprovedFormsEntity, Long> {

    ApprovedFormsEntity findAllByUserIdAndBursaryMonth(Long userId, String bursaryMonth);


}
