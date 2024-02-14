package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Query("SELECT new com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity(" +
            "c.userValuesId, c.chiefUserName, c.userId, c.bursaryMonth, c.status)" +
            " FROM ChiefDataEntity c WHERE " +
            "c.chiefUserName= :chiefUserName AND c.bursaryMonth= :bursaryMonth AND c.status= :status")
    List<ChiefDataEntity> findAllByChiefUserNameAndBursaryMonth(String chiefUserName, String bursaryMonth,Boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE ChiefDataEntity c SET c.status= :status WHERE c.userId= :userId AND c.bursaryMonth = :bursaryMonth")
    void updateStatusByUserId(Boolean status, Long userId, String bursaryMonth);
}
