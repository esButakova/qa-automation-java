package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import static com.tinkoff.edu.app.enums.LoanResponseType.APPROVED;
import static com.tinkoff.edu.app.enums.LoanResponseType.DENIED;
import static com.tinkoff.edu.app.enums.LoanType.*;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        if (request == null) {
            return new LoanResponse(DENIED, request, -1);
        }
        if ((request.getMonths() <= 0) || (request.getAmount() <= 0)) {
            return new LoanResponse(DENIED, request, -1);
        }
        LoanResponseType responseType = calculateResponseType(request);
        return new LoanResponse(responseType, request, ++requestId);
    }

    public LoanResponseType calculateResponseType(LoanRequest request) {
        if (request.getType().equals(person)) {
            if (request.getAmount() <= 10000) {
                if (request.getMonths() <= 12) {
                    return APPROVED;
                } else {
                    //непокрытая ветка
                    return DENIED;
                }
            } else {
                if (request.getMonths() > 12) {
                    return DENIED;
                } else {
                    //непокрытая ветка
                    return DENIED;
                }
            }
        }

        if (request.getType().equals(ooo)) {
            if (request.getAmount() <= 10000) {
                return DENIED;
            } else {
                if (request.getMonths() < 12) {
                    return APPROVED;
                } else {
                    return DENIED;
                }
            }
        }

        if (request.getType().equals(ip)) {
            return DENIED;
        }

        return DENIED;
    }

}