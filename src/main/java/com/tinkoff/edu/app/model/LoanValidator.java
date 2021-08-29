package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.constants.ValidatorConstants;
import com.tinkoff.edu.app.enums.LoanType;


/**
 * Created on 18.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */
public class LoanValidator {

    public static boolean validate(LoanRequest request) {
        if (request.getType() == LoanType.person) {
            if (request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE
                    && (request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE
                    && request.getAmount() <= ValidatorConstants.AMOUNT_MAX_VALUE)
                    && (request.getMonths() > ValidatorConstants.MONTHS_MIN_VALUE
                    && request.getMonths() <= ValidatorConstants.MONTHS_MAX_VALUE)) {
                return true;
            }
            if (request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE
                    && (request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE
                    && request.getAmount() > ValidatorConstants.AMOUNT_MAX_VALUE)
                    || (request.getMonths() > ValidatorConstants.MONTHS_MIN_VALUE &&
                    request.getMonths() > ValidatorConstants.MONTHS_MAX_VALUE)) {
                return false;
            }
        } else if (request.getType() == LoanType.ooo) {
            if ((request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE)
                    && ((request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE)
                    && (request.getAmount() <= ValidatorConstants.AMOUNT_MAX_VALUE))) {
                return false;
            }
            if ((request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE)
                    && ((request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE)
                    && (request.getAmount() > ValidatorConstants.AMOUNT_MAX_VALUE))
                    && (request.getMonths() > ValidatorConstants.MONTHS_MIN_VALUE &&
                    request.getMonths() < ValidatorConstants.MONTHS_MAX_VALUE)) {
                return true;
            }
            if (request.getClientId() >= ValidatorConstants.CLIENT_ID_MIN_VALUE
                    && (request.getAmount() > ValidatorConstants.AMOUNT_MIN_VALUE
                    && request.getAmount() > ValidatorConstants.AMOUNT_MAX_VALUE)
                    || (request.getMonths() > ValidatorConstants.MONTHS_MIN_VALUE &&
                    request.getMonths() >= ValidatorConstants.MONTHS_MAX_VALUE)) {
                return false;
            }
        } else if (request.getType() == LoanType.ip) {
            return false;
        }
        return false;
    }
}