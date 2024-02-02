package com.bree.springproject.onlinebursaryapplication.ControllerAdvice;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.models.ExceptionModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@ControllerAdvice
@Setter
public class ExceptionHandling {

    private ExceptionModel exceptionModel;

    @ExceptionHandler(value = UserDoesNotExistException.class)
   public ResponseEntity<ExceptionModel> handleUserNonExistence(String message)
    {
        exceptionModel.setMessage(message);
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_ACCEPTABLE);
    }

}
