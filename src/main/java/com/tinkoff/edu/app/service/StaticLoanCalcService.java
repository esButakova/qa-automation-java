package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class StaticLoanCalcService implements LoanCalcService {
    private final LoanCalcRepository loanCalcRepositry;

    public StaticLoanCalcService(LoanCalcRepository loanCalcRepositry) {
        this.loanCalcRepositry = loanCalcRepositry;
    }

    /**
     * Кредитный калькулятор.
     */
    @Override
    public LoanResponse createRequest(LoanRequest request) {
        return loanCalcRepositry.save(request);
    }
}
