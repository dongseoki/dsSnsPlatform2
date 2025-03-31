package com.dssns.common.exception;

import com.dssns.common.web.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<?> handleResponseException(ServiceException ex) {
    ex.printStackTrace();
    return ApiResponse.ResponseException(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));
    ex.printStackTrace();
    return ApiResponse.ValidException("VALIDATE_ERROR", String.valueOf(errors));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<?> bindException(BindException ex) {
    AtomicReference<String> errors = new AtomicReference<>("");
    ex.getBindingResult().getAllErrors().forEach(c -> errors.set(c.getDefaultMessage()));
    ex.printStackTrace();
    return ApiResponse.ValidException("VALIDATE_ERROR", String.valueOf(errors));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> serverException(Exception ex) {
    ex.printStackTrace();
    return ApiResponse.ServerException("SERVER_ERROR", ex.getMessage());
  }
}
