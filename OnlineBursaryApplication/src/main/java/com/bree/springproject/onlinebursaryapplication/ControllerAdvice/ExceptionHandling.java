package com.bree.springproject.onlinebursaryapplication.ControllerAdvice;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.*;
import com.bree.springproject.onlinebursaryapplication.models.ExceptionModel;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleJSONValidation(MethodArgumentNotValidException e)
    {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorMap.put(field, message);
    });


        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WeakPasswordException.class)
    public ResponseEntity<ExceptionModel> handleWeakPasswordException(WeakPasswordException passwordException)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setMessage(passwordException.getMessage());
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserExistException.class)
    public ResponseEntity<ExceptionModel> handleUserExistException(UserExistException existException)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(existException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidPhoneNumberException.class)
    public ResponseEntity<ExceptionModel> handleInvalidPhoneNumberException(InvalidPhoneNumberException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ExceptionModel> handleSmtpExceptions(ConnectException connectException)
    {
        //handle smtp time out exception due to failure for connections.

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage("Connection Problem, Please checkout on you internet connection");
        exceptionModel.setHttpStatus(HttpStatus.REQUEST_TIMEOUT);
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ExceptionModel> handleConnectionFailure(UnknownHostException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setMessage(String.valueOf(e.getMessage()));
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);

        return new ResponseEntity<>(exceptionModel, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(InvalidUpdateException.class)
    public  ResponseEntity<ExceptionModel> handleInvalidUpdate(InvalidUpdateException exception)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setDate(new Date());
        exceptionModel.setMessage(exception.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionModel> handleInvalidJson
            (HttpMessageNotReadableException httpMessageNotReadableException)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage("The JSon Passed is invalid => "+httpMessageNotReadableException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ExceptionModel> handleNumberFormatException(NumberFormatException numberFormatException)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(numberFormatException.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setDate(new Date());


        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionModel> handleIllegalArgumentException
            (IllegalArgumentException illegalArgumentException)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage("Check On The Month Entered => "+illegalArgumentException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormNotFoundException.class)
    public ResponseEntity<ExceptionModel>
    handleFormNotFoundException(FormNotFoundException formNotFoundException)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(formNotFoundException.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }
}
