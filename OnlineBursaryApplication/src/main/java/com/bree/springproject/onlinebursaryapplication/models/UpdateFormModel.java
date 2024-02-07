package com.bree.springproject.onlinebursaryapplication.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UpdateFormModel {

    @NotNull
    private Long formId;

    @NotNull
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
