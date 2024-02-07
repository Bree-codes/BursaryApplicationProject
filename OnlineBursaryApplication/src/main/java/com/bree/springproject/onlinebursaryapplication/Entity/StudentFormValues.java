package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentFormValues {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long valueId;

    private Long StudentId;

    private Long fieldId;

    private String fieldValue;

    private String formIdentify;
}
