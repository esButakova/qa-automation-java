package com.tinkoff.edu.app;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class StaticLoanCalcService implements LoanCalcService{
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
