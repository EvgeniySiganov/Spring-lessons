package ru.iteco.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.account.model.dto.BankBookErrorDto;
import ru.iteco.account.model.exception.DoublingException;
import ru.iteco.account.model.exception.NotFoundException;
import ru.iteco.account.model.exception.OperationException;

@RestControllerAdvice
public class BankBookExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BankBookErrorDto> handlerUserNotFoundException(NotFoundException exception){
        BankBookErrorDto bankBookErrorDto = new BankBookErrorDto("NOT FOUND", exception.getIdNotFound(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(bankBookErrorDto);
    }

    @ExceptionHandler(DoublingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BankBookErrorDto> handlerDoublingException(DoublingException exception){
        BankBookErrorDto bankBookErrorDto = new BankBookErrorDto("BANK BOOK IS ALREADY EXIST", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(bankBookErrorDto);
    }

    @ExceptionHandler(OperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BankBookErrorDto> handlerOperationException(OperationException exception){
        BankBookErrorDto bankBookErrorDto = new BankBookErrorDto("NUMBER IS ALREADY EXIST", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(bankBookErrorDto);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BankBookErrorDto> handlerMissingPathVariableException(MissingPathVariableException exception){
        BankBookErrorDto bankBookErrorDto = new BankBookErrorDto(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(bankBookErrorDto);
    }

}
