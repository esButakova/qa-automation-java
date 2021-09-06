package com.tinkoff.edu.app.exception;

/**
 * Created on 06.09.2021
 *
 * @author Elena Butakova
 */
public class LoanServiceException extends IllegalArgumentException {
    public LoanServiceException(String s) {
        super(s);
    }

    public LoanServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
