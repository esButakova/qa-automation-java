package com.tinkoff.edu.app;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcRepository {
    private static int requestId = 0;

    /**
     * @return Id запроса
     */
    public static int save(LoanRequest request) {
        return ++requestId;
    }
}
