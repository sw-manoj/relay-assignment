package com.relay.iot.sensor.api.advice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.relay.iot.sensor.api.exception.BaseException;
import com.relay.iot.sensor.api.exception.NotFoundException;
import com.relay.iot.sensor.api.model.error.BaseErrorMessages;
import com.relay.iot.sensor.api.model.error.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class SensorApiGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception,
                                                                    WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorMessage> handlerMismatchedException(MismatchedInputException exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND, BaseErrorMessages.GENERIC_METHOD_NOT_ALLOWED.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(HttpStatus.NOT_FOUND, BaseErrorMessages.GENERIC_METHOD_NOT_ALLOWED.getMessage()));
    }
}
