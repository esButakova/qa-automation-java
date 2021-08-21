package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;
import static com.tinkoff.edu.app.LoanValidator.validate;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcController {
    /**
     * Валидацция и журналирование запроса.
     */
    public static int createRequest(LoanRequest request) {
        log("Информация", request);
        if (validate(request)) {
            log("Информация", "Валидация завершена. Решение: "+LoanResponseType.APPROVED);
            return LoanCalcService.createRequest(request);
        } else {
            log("Ошибка", "Валидация завершена. Решение: "+LoanResponseType.DENIED);
            return -1;
        }


    }
}
