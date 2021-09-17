package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private static final Map<UUID, LoanResponse> loans = new HashMap<>();

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanResponse response) {
        loans.put(response.getId(), response);
        return response;
    }

    @Override
    public void update(LoanResponse response) {
        save(response);
    }

    @Override
    public LoanResponse find(UUID uuid) {
        return loans.get(uuid);
    }

    @Override
    public List<LoanResponse> findByType(LoanType type) {
        return loans.values().stream()
                .filter(loanResponse -> type.equals(loanResponse.getRequest().getType()))
                .collect(Collectors.toList());
    }
}