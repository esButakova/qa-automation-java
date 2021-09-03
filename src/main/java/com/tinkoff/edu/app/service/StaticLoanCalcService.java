package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
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
public class StaticLoanCalcService implements LoanCalcService {
    private final LoanCalcRepository loanCalcRepository;

    public StaticLoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    /**
     * Кредитный калькулятор.
     */
    @Override
    public LoanResponse createRequest(LoanRequest request) {

        if (isRequestValid(request)) {
            LoanResponseType loanStatus = calculateResponseType(request);
            LoanResponse loan = new LoanResponse(loanStatus, request);
            return loanCalcRepository.save(loan);
        } else {
            return null;
        }
    }

    @Override
    public LoanResponseType getLoanStatus(UUID uuid) {
        LoanResponseType status = null;
        LoanResponse loan = loanCalcRepository.find(uuid);
        if (loan != null) {
            status = loan.getResponseType();
        }
        return status;
    }

    @Override
    public LoanResponseType updateLoanStatus(UUID uuid, LoanResponseType loanType) {
        LoanResponseType status = null;
        LoanResponse loan = loanCalcRepository.find(uuid);
        if (loan != null) {
            loan.setResponseType(loanType);
            loanCalcRepository.update(loan);
            status = loan.getResponseType();
        }
        return status;
    }

    private boolean isRequestValid(LoanRequest request) {
        if (request == null) {
            return false;
        }
        if ((request.getMonths() <= 0) || (request.getAmount() <= 0)) {
            return false;
        }
        return true;
    }


    private LoanResponseType calculateResponseType(LoanRequest request) {
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
