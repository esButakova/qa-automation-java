package com.tinkoff.edu.app;


/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcService {
    /**
     * Кредитный калькулятор.
     */
    public LoanResponse createRequest(LoanRequest request) {
        //тут добавить условие  на заявку
        LoanCalcRepository loanCalcRepositry = new LoanCalcRepository();
        return loanCalcRepositry.save(request);
    }
}
