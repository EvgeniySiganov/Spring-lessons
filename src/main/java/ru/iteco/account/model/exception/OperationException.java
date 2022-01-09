package ru.iteco.account.model.exception;

public class OperationException extends RuntimeException{

    public OperationException(String message) {
        super(message);
    }
}
