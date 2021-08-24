package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public interface LoanCalcService {
    LoanResponse createRequest(LoanRequest request);
}
