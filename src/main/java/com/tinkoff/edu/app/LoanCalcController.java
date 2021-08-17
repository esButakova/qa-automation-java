package com.tinkoff.edu.app;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
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
