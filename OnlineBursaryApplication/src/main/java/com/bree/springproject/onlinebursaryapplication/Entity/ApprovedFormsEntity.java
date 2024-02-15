package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApprovedFormsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ApprovedFormsId;

    Long userId;

    String bursaryMonth;

    Boolean status;

}
