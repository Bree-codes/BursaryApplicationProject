package com.bree.springproject.onlinebursaryapplication.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FormFields {
    private String fieldName;

    private String fieldType;
}
