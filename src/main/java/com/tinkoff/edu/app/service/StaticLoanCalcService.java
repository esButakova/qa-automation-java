package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.UUID;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class StaticLoanCalcService implements LoanCalcService {
    private final LoanCalcRepository loanCalcRepository;

    public StaticLoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    /**
     * Кредитный калькулятор.
     */
    @Override
    public LoanResponse createRequest(LoanRequest request) {
        return loanCalcRepository.save(request);
    }

    @Override
    public LoanResponseType getLoanStatus(UUID uuid) {
        return loanCalcRepository.getLoanStatus(uuid);
    }

    @Override
    public LoanResponseType updateLoanStatus(UUID uuid, LoanResponseType loanType) {
        return loanCalcRepository.updateLoanStatus(uuid, loanType);
    }
}
