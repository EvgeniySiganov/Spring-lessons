package ru.iteco.account.model.exception;

public class DoublingException extends RuntimeException {

    public DoublingException(String message) {
        super(message);
    }
}
