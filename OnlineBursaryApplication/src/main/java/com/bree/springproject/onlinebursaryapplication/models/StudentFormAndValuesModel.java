package com.bree.springproject.onlinebursaryapplication.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StudentFormAndValuesModel {

    private String fieldInputType;

    private String fieldValue;

    private Long fieldId;

}
