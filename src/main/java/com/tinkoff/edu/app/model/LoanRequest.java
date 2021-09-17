package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanType;

import java.io.Serializable;
import java.util.Objects;


/**
 * Created on 17.08.2021
 *
 * @author Elena Butakova
 *
 * Валидатор кредитной заявки
 */
public class LoanRequest{
    private final LoanType type;
    private final int months;
    private final int amount;
    private final String fullName;

    public LoanRequest() {
        this(null, 0, 0, null);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest)) return false;
        LoanRequest that = (LoanRequest) o;
        return months == that.months && amount == that.amount && type == that.type && fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, months, amount, fullName);
    }
}