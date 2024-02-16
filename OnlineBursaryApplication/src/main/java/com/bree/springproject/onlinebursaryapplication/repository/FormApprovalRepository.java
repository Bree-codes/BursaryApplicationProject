package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ApprovedFormsEntity;
import org.springframework.data.repository.CrudRepository;

public interface FormApprovalRepository extends CrudRepository<ApprovedFormsEntity, Long> {


}
