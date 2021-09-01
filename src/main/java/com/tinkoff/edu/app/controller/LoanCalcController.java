package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.service.LoanCalcService;

import java.util.UUID;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcController {

    private final LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;
    }


    public LoanResponseType getLoanStatus(UUID uuid) {
        return service.getLoanStatus(uuid);
    }


    public LoanResponseType updateLoanStatus(UUID uuid, LoanResponseType loanType) {
        return service.updateLoanStatus(uuid, loanType);
    }

    /**
     * Валидацция и журналирование запроса.
     */
    public LoanResponse createRequest(LoanRequest request) {
        log("Событие:", request);
        return service.createRequest(request);
    }
}