package com.bree.springproject.onlinebursaryapplication.repository;

import com.bree.springproject.onlinebursaryapplication.Entity.UserNotificationTable;
import org.springframework.data.repository.CrudRepository;

public interface UserNotificationsRepository extends CrudRepository<UserNotificationTable, Long> {

}
