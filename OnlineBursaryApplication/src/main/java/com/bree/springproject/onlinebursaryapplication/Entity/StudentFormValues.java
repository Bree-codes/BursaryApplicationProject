package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class StudentFormValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long valueId;

    @NotNull
    private Long userId;

    @NotNull
    @Column(unique = true)
    private Long fieldId;

    @NotNull
    private String fieldValue;

    @NotNull
    private String bursaryMonth;

}
