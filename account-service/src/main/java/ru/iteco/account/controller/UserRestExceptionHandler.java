package ru.iteco.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.account.model.dto.ErrorDto;
import ru.iteco.account.model.exception.UserNotFoundException;

@RestControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlerUserNotFoundException(UserNotFoundException exception){
        ErrorDto errorDto = new ErrorDto("NOT FOUND", exception.getIdNotFound(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }


}
