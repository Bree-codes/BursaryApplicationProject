package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

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
