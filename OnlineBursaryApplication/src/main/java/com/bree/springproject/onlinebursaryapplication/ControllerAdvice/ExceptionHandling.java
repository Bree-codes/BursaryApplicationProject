package com.bree.springproject.onlinebursaryapplication.ControllerAdvice;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.models.ExceptionModel;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


@ControllerAdvice
@Setter
public class ExceptionHandling {

    @ExceptionHandler(value = UserDoesNotExistException.class)
   public ResponseEntity<ExceptionModel> handleUserNonExistence(UserDoesNotExistException userDoesNotExistException)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(userDoesNotExistException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

}
