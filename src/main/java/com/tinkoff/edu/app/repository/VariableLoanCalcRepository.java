package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.UUID;

import static com.tinkoff.edu.app.enums.LoanResponseType.APPROVED;
import static com.tinkoff.edu.app.enums.LoanResponseType.DENIED;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class VariableLoanCalcRepository implements LoanCalcRepository {
    private int lastElementId = 0;
    LoanResponse[] loans = new LoanResponse[10000];

    /**
     * TODO persist request
     *
     * @return Request Id
     */
    @Override
    public LoanResponse save(LoanRequest request) {
        if (request == null) {
            return new LoanResponse(DENIED, null);
        }
        if ((request.getMonths() <= 0) || (request.getAmount() <= 0)) {
            return new LoanResponse(DENIED, request);
        }

        LoanResponseType responseType = calculateResponseType(request);
        LoanResponse response = new LoanResponse(responseType, request);

        loans[lastElementId++] = response;

        return response;
    }

    @Override
    public LoanResponseType getLoanStatus(UUID uuid) {
        for (int i = 0; i < loans.length && i < lastElementId; i++) {
            if (loans[i].getId().equals(uuid)) {
                return loans[i].getResponseType();
            }
        }
        return null;
    }

    @Override
    public LoanResponseType updateLoanStatus(UUID uuid, LoanResponseType loanType) {
        for (int i = 0; i < loans.length && i < lastElementId; i++) {
            if (loans[i].getId().equals(uuid)) {
                loans[i].setResponseType(loanType);
                return loans[i].getResponseType();
            }
        }
        return null;
    }

    public LoanResponseType calculateResponseType(LoanRequest request) {
        LoanResponseType responseType = null;

        switch (request.getType()) {
            case person: {

                if (request.getAmount() <= 10000) {
                    if (request.getMonths() <= 12) {
                        responseType = APPROVED;
                    } else {
                        //непокрытая ветка
                        responseType = DENIED;
                    }
                } else {
                    if (request.getMonths() > 12) {
                        responseType = DENIED;
                    } else {
                        //непокрытая ветка
                        responseType = DENIED;
                    }
                }
                break;
            }

            case ooo: {
                if (request.getAmount() <= 10000) {
                    responseType = DENIED;
                } else {
                    if (request.getMonths() < 12) {
                        responseType = APPROVED;
                    } else {
                        responseType = DENIED;
                    }
                }
                break;
            }

            case ip: {
                responseType = DENIED;
                break;
            }
        }
        return responseType;
    }
}