package com.bree.springproject.onlinebursaryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class StudentFormAndValuesModel {

    private String fieldInputType;

    private String fieldValue;

    private Long fieldId;

    private Long section;

    private String fieldName;

    private String bursaryMonth;


}
