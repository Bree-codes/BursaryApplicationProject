package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long notificationsId;

    Long userId;

    String message;

    Boolean status;

    @PrePersist
    public void setDefaults()
    {
        if(status == null)
        {
            status = false;
        }
    }

}
