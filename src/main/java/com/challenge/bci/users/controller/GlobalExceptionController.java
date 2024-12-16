package com.challenge.bci.users.controller;

import com.challenge.bci.users.dto.ErrorDto;
import com.challenge.bci.users.exceptions.CustomInformationException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(CustomInformationException.class)
  public ErrorDto handleAnyException(CustomInformationException exception){
    log.warn("==============handleAnyException=============");
    Map<String, String> map = new HashMap<>();
    map.put("Error", exception.getMessage());
    return ErrorDto.builder()
        .mensaje(map)
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorDto handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    log.warn("==============Validation Request=============");
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ErrorDto.builder()
        .mensaje(errors)
        .build();
  }
}
