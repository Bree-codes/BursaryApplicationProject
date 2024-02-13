package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class ChiefDataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    Long userValuesId;

    @Column(unique = true)
    @NotNull
    @NotBlank
    String chiefUserName;

    @Column(unique = true)
    @NotNull
    @NotBlank
    String userId;

    @NotNull
    @NotBlank
    String bursaryMonth;

}
