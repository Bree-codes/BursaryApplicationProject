package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiefDataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Long userValuesId;

    @Column(unique = true)
    String chiefUserName;

    @Column(unique = true)
    Long userId;

    String bursaryMonth;

    Boolean status;

    public ChiefDataEntity(String chiefUserName, Long userId, String bursaryMonth)
    {
        this.bursaryMonth = bursaryMonth;
        this.userId = userId;
        this.chiefUserName =  chiefUserName;
    }

    @PrePersist
    public void setDefaultStatus()
    {
        if(status == null)
        {
            status = false;
        }
    }
}
