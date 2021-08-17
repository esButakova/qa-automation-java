package com.tinkoff.edu.app;

/**
 * Created on 18.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */

public class LoanValidator {
    private static final int CLIENT_ID_MIN_VALUE = 0;
    private static final int AMOUNT_MIN_VALUE = 0;
    private static final int AMOUNT_MAX_VALUE = 5000000;
    private static final int MONTHS_MIN_VALUE = 0;
    private static final int MONTHS_MAX_VALUE = 60;

    public static boolean validate(LoanRequest request) {
        if (request.getClientId() >= CLIENT_ID_MIN_VALUE
                && (request.getAmount() > AMOUNT_MIN_VALUE && request.getAmount() < AMOUNT_MAX_VALUE)
                && (request.getMonths() > MONTHS_MIN_VALUE && request.getMonths() < MONTHS_MAX_VALUE)) {
            return true;
        } else {
            return false;
        }
    }
}
