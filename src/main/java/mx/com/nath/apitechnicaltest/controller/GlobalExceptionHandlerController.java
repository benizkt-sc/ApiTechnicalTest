package mx.com.nath.apitechnicaltest.controller;

import mx.com.nath.apitechnicaltest.exception.ShowNotFoundException;
import mx.com.nath.apitechnicaltest.model.ErrorInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ShowNotFoundException.class)
    ResponseEntity<ErrorInformation> handleShowNotFoundException(final ShowNotFoundException exception) {
        return ResponseEntity.status(500).body(new ErrorInformation(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> handleValidationException(final MethodArgumentNotValidException exception) {
        final var messages = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity
                .badRequest()
                .body(messages);
    }

}
