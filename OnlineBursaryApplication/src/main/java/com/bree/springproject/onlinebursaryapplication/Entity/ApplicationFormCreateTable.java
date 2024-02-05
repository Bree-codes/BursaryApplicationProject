package com.bree.springproject.onlinebursaryapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
public class ApplicationFormCreateTable {

    @Id
    @Generated
    private Long formId;

    private String bursaryMonth;

    @NotNull
    private String Field;

    @NotNull
    private String type;

    @NotNull
    private Long userId;

    @NotNull
    private String section;

}
