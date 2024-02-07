package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class ApplicationFormCreateTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long fieldId;

    private String bursaryMonth;

    @NotNull
    private String fieldName;

    @NotNull
    private String fieldInputType;

    @NotNull
    private Long userId;

    @NotNull
    private String section;

}
