package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class StaticVariableLoanCalcRepository implements LoanCalcRepository {
    private static int requestId = 0;

    /**
     * @return объект LoanResponse
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        return new LoanResponse(LoanResponseType.APPROVED,request, ++requestId);
    }

    public static int getRequestId() {
        return requestId;
    }
}
