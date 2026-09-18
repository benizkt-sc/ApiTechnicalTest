package mx.com.nath.apitechnicaltest.controller;

import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.model.ErrorInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ShowNotFoundException.class)
    ResponseEntity<ErrorInformation> handleShowNotFoundException(final ShowNotFoundException exception) {
        return ResponseEntity.status(500).body(new ErrorInformation(exception.getMessage()));
    }

}
