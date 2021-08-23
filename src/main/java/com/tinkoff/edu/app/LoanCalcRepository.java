package com.tinkoff.edu.app;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcRepository {
    private static int requestId = 0;

    /**
     * @return объект LoanResponse
     */
    public LoanResponse save(LoanRequest request) {
        return new LoanResponse(LoanResponseType.APPROVED,request, ++requestId);
    }
}
