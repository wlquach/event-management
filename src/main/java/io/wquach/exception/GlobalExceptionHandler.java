package io.wquach.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleEncryptionException(EmptyResultDataAccessException exception) {
    ErrorMessage err = new ErrorMessage();
    err.errorMessage = "No records found";

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }
}