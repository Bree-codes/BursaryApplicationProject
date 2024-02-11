package com.bree.springproject.onlinebursaryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentFormAndValuesModel {

    private String fieldInputType;

    private String fieldValue;

    private Long fieldId;

    private String section;

    private String fieldName;

    private String bursaryMonth;

    private Long valueId;

}
