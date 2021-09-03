package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanResponse;

import java.util.UUID;


/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private static int lastElementId = 0;
    private static LoanResponse[] loans = new LoanResponse[10000];

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanResponse response) {

        loans[lastElementId++] = response;
        return response;
    }

    @Override
    public void update(LoanResponse response) {
        UUID uuid = response.getId();
        for (int i = 0; i < loans.length && i < lastElementId; i++) {
            if (loans[i].getId().equals(uuid)) {
                loans[i] = response;
            }
        }
    }

    @Override
    public LoanResponse find(UUID uuid) {
        for (int i = 0; i < loans.length && i < lastElementId; i++) {
            if (loans[i].getId().equals(uuid)) {
                return loans[i];
            }
        }
        return null;
    }
}