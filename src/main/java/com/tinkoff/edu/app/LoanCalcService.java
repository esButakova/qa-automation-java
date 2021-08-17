package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcRepository.save;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcService {
    /**
     * Кредитный калькулятор.
     */
    public static int createRequest(LoanRequest request) {
        //тут добавить условие  на заявку
        return save(request);
    }
}
