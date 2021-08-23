package com.tinkoff.edu.app;

/**
 * Created on 18.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */

public class LoanValidator {

    public static boolean validate(LoanRequest request) {
        if (request.getClientId() >= Constants.CLIENT_ID_MIN_VALUE
                && (request.getAmount() > Constants.AMOUNT_MIN_VALUE && request.getAmount() < Constants.AMOUNT_MAX_VALUE)
                && (request.getMonths() > Constants.MONTHS_MIN_VALUE && request.getMonths() < Constants.MONTHS_MAX_VALUE)) {
            return true;
        } else {
            return false;
        }
    }
}
