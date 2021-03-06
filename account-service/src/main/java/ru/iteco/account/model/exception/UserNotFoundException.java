package ru.iteco.account.model.exception;

public class UserNotFoundException extends RuntimeException{

    private Integer idNotFound;

    public UserNotFoundException(String message, Integer idNotFound) {
        super(message + idNotFound);
        this.idNotFound = idNotFound;
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getIdNotFound() {
        return idNotFound;
    }
}
