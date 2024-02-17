package com.bree.springproject.onlinebursaryapplication.ControllerAdvice;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.*;
import com.bree.springproject.onlinebursaryapplication.models.ExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.IllegalSelectQueryException;
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
import java.util.NoSuchElementException;


@ControllerAdvice
@Slf4j
public class ExceptionHandling {

    @ExceptionHandler(UserDoesNotExistException.class)
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

        exceptionModel.setMessage("Connection Problem, Please checkout on you internet connection => "+
                connectException.getMessage());
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

    @ExceptionHandler(InvalidFieldIdProvidedException.class)
    public ResponseEntity<ExceptionModel>
    handleInvalidFieldIdException(InvalidFieldIdProvidedException invalidFieldIdProvidedException){

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setMessage(invalidFieldIdProvidedException.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldValuesAlreadyExistException.class)
    public ResponseEntity<ExceptionModel> handleAttemptToStoreDuplicateValues(
            FieldValuesAlreadyExistException e){

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DuplicateFormFieldException.class)
    public ResponseEntity<ExceptionModel> handleDuplicateFieldException(DuplicateFormFieldException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setDate(new Date());
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoFormAvailableException.class)
    public ResponseEntity<ExceptionModel> handleNoFormAvailableException(NoFormAvailableException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalSelectQueryException.class)
    public ResponseEntity<ExceptionModel> handleIllegalSelectQueryException(IllegalSelectQueryException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return  new ResponseEntity<>(exceptionModel,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserFieldDoesNotExistException.class)
    public ResponseEntity<ExceptionModel> handlerUserFieldDoesNotExistException(UserFieldDoesNotExistException e){
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotAChiefException.class)
    public ResponseEntity<ExceptionModel> handleUserNotAChiefException(UserNotAChiefException e)
    {
        ExceptionModel  exceptionModel = new ExceptionModel();

        log.error("Unauthorized request, Username entered Not a chief");

        exceptionModel.setDate(new Date());
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FormAlreadySentException.class)
    public ResponseEntity<ExceptionModel> handleFormAlreadySentException(FormAlreadySentException e)
    {
        log.error("Attempt to send duplicate forms.");

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());

        return  new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorisedRequestException.class)
    public ResponseEntity<ExceptionModel> handleUnauthorisedRequestException(UnauthorisedRequestException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);
        exceptionModel.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionModel, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionModel> handleNoSuchElementException(NoSuchElementException e)
    {
        log.info("The Passed chief id does not exist");

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);
        exceptionModel.setDate(new Date());


        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormAlreadyApprovedException.class)
    public ResponseEntity<ExceptionModel> handleFormAlreadyApprovedException(
            FormAlreadyApprovedException e)
    {

        log.error("Attempt to approve the same form twice.");
        /*Prepare the response model*/
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);
        exceptionModel.setMessage(e.getMessage());

        log.info("Exception handled");
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRequestNotAuthorised.class)
    public ResponseEntity<ExceptionModel> handleUserRequestNotAuthorised(UserRequestNotAuthorised e)
    {
        log.error("Non Admin user Attempt to perform admin operations");
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.UNAUTHORIZED);
        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());

        log.info("Exception handled");
        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }
}
