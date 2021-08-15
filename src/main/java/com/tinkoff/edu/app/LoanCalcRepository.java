package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId;

    /**
     * @return  Id запроса
     */
    public static int save() {
        int localVar=++requestId;
        return localVar;
    }
}
