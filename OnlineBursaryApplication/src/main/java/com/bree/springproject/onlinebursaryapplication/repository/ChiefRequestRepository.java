package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChiefRequestRepository extends CrudRepository<ChiefDataEntity, Long> {


    /*
    * The query enables us to get the existing,
    *I used this query for:
    * 1. Checking whether the user form has already been sent.
    * 2. Loading the form to the chief.*/
    ChiefDataEntity findByUserIdAndBursaryMonth(Long userId, String bursaryMonth);

    @Query("SELECT DISTINCT C.bursaryMonth FROM ChiefDataEntity C")
    List<String> findDistinctBursaryMonth();

    List<ChiefDataEntity> findAllByChiefUserNameAndBursaryMonth(String chiefUserName, String latestMonth);
}