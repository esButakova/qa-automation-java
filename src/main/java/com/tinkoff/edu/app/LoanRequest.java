package com.tinkoff.edu.app;

/**
 * Created on 17.08.2021
 *
 * @author Elena Butakova
 * <p>
 * Валидатор кредитной заявки
 */
public class LoanRequest {
    private final LoanType type;
    private final int months;
    private final int amount;
    private final int clientId;

    public LoanRequest(LoanType type, int clientId, int months, int amount) {
        this.type = type;
        this.clientId = clientId;
        this.months = months;
        this.amount = amount;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public int getClientId() {
        return clientId;
    }

    public String toString() {
        return "RQ:{"
                + this.type + ", " + this.getClientId() + ", "
                + this.getAmount() + " for " + this.getMonths() + '}';
    }
}
