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
    public LoanResponse createRequest(LoanRequest request) {
        LoanCalcService service = new LoanCalcService();
        log("Информация", request);
        if (validate(request)) {
            log("Информация", "Валидация завершена. Решение: " + LoanResponseType.APPROVED);
            return service.createRequest(request);
        } else {
            log("Ошибка", "Валидация завершена. Решение: " + LoanResponseType.DENIED);
            return new LoanResponse(LoanResponseType.DENIED, request, -1);
        }
    }
}
