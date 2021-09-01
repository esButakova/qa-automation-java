package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanType;


/**
 * Created on 17.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */
public class LoanRequest {
    private final LoanType type;
    private final int months;
    private final int amount;
    private final String fullName;

    public LoanRequest(LoanType type, int months, int amount, String fullName) {
        this.type = type;
        this.months = months;
        this.amount = amount;
        this.fullName = fullName;
    }

    public LoanType getType() {
        return type;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public String getFullName() {
        return fullName;
    }


    public String toString() {
        return "RQ:{"
                + this.type + ", "
                + this.fullName + ", "
                + this.amount + " for " + this.months + '}';
    }
}