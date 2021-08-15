package com.tinkoff.edu.app;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcRepository {
    private static int requestId;

    /**
     * @return Id запроса
     */
    public static int save() {
        int localVar = ++requestId;
        return localVar;
    }
}
