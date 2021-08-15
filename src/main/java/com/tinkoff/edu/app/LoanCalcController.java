package com.tinkoff.edu.app;

public class LoanCalcController {
    /**
     * Валидацция и журналирование запроса.
     */
    public static int createRequest() {
        int localVar;
        //Валидация параметров
        //Логирование
        LoanCalcLogger.log();
        return LoanCalcService.createRequest();
    }
}
