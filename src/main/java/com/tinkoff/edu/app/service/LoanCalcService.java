package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.UUID;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public interface LoanCalcService {
    LoanResponse createRequest(LoanRequest request);

    LoanResponseType getLoanStatus(UUID uuid);

    LoanResponseType updateLoanStatus(UUID uuid, LoanResponseType loanType);
}
