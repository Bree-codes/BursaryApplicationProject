package com.bree.springproject.onlinebursaryapplication.userDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterUserDTO {


    @NotNull(message = "The userName Can Not Be null")
    @NotBlank(message = "The userName can Not Be Blank")
    private String userName;


    private String userEmail;

    @NotNull(message = "The userPhoneNumber Can Not Be null")
    @NotBlank(message = "The userPhoneNumber can Not Be Blank")
    @Size(min = 10, max=13, message = "The Phone Number Should Range From 10 to 13 numbers")
    private String userPhoneNumber;

    @NotNull(message = "The userPassword Can Not Be null")
    @NotBlank(message = "The userPassword can Not Be Blank")
    @Size(min = 8, max = 20, message = "The userPassword Should Range From 8 Characters to 20 Characters")
    private String userPassword;
}
