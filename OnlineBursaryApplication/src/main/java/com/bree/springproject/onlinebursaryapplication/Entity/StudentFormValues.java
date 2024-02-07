package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class StudentFormValues {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long valueId;

    @NotNull
    private Long userId;

    @NotNull
    private Long fieldId;

    @NotNull
    private String fieldValue;

    @NotNull
    private String bursaryMonth;
}
