package ru.iteco.account.homeworkthree.controller;

public class NotFoundException extends RuntimeException{

    private Integer idNotFound;

    public NotFoundException(String message, Integer idNotFound) {
        super(message + " by ID " + idNotFound);
        this.idNotFound = idNotFound;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getIdNotFound() {
        return idNotFound;
    }
}
