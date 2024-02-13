package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.ChiefDataEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChiefRequestRepository extends CrudRepository<ChiefDataEntity, Long> {
}
