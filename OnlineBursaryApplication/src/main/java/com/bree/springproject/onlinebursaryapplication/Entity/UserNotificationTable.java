package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationTable {

    @Id
    Long notificationsId;

    Long userId;

    String message;

    Boolean status;
}
