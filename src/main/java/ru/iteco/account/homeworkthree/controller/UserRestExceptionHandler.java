package ru.iteco.account.homeworkthree.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.account.homeworkthree.model.ErrorDto;

@RestControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handlerUserNotFoundException(NotFoundException exception){
        ErrorDto errorDto = new ErrorDto("NOT FOUND", exception.getIdNotFound(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(DoublingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlerDoublingException(DoublingException exception){
        ErrorDto errorDto = new ErrorDto("BANK BOOK IS ALREADY EXIST", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(OperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlerOperationException(OperationException exception){
        ErrorDto errorDto = new ErrorDto("NUMBER IS ALREADY EXIST", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handlerMissingPathVariableException(MissingPathVariableException exception){
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDto);
    }

}
