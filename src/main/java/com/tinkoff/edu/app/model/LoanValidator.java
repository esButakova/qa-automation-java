package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.constants.ValidatorConstants;

/**
 * Created on 18.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */
public class LoanValidator {

    public static boolean validate(LoanRequest request) {
        if (request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE
                && (request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE && request.getAmount() < ValidatorConstants.AMOUNT_MAX_VALUE)
                && (request.getMonths() > ValidatorConstants.MONTHS_MIN_VALUE && request.getMonths() < ValidatorConstants.MONTHS_MAX_VALUE)) {
            return true;
        } else {
            return false;
        }
    }
}
