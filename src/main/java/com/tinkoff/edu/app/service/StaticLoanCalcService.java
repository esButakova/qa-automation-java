package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.exception.LoanServiceException;
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

        try {
            validateRequest(request);
            LoanResponseType loanStatus = calculateResponseType(request);
            LoanResponse loan = new LoanResponse(loanStatus, request);
            return loanCalcRepository.save(loan);
        } catch (IllegalArgumentException e) {
            throw new LoanServiceException("Ошибка валидации запроса", e);
        }
        /*


        if (isRequestValid(request)) {
            LoanResponseType loanStatus = calculateResponseType(request);
            LoanResponse loan = new LoanResponse(loanStatus, request);
            return loanCalcRepository.save(loan);
        } else {
            return null;
        }*/
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

    private void validateRequest(LoanRequest request) {
        String validationResult = getRequestValidationMessage(request);
        if (validationResult != null) {
            throw new IllegalArgumentException(validationResult);
        }
    }

    private String getRequestValidationMessage(LoanRequest request) {
        if (request == null) {
            // return false;
            //throw new IllegalArgumentException("Пустой запрос");
            return "Пустой запрос";
        }

        if (request.getFullName() == null || !isFullNameValid(request.getFullName())) {
            return "Неправильно задано имя.";
        }

        if (request.getAmount() < 0.01 || request.getAmount() > 999_999.99) {
            return "Некорректная запрашиваемая сумма";
        }
        if (request.getMonths() < 1 || request.getMonths() > 100) {
            return "Некорректно задан срок";
        }
        return null;
    }

    private boolean isFullNameValid(String name) {

        if (name.length() < 10 || name.length() > 100) {
            return false;
        }

        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c) && Character.compare(c, '-') != 0) {
                return false;
            }
        }
        return true;
    }

    private LoanResponseType calculateResponseType(LoanRequest request) {
        switch (request.getType()) {
            case person: {

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

            case ooo: {
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

            case ip: {
                return DENIED;
            }

            default: {
                throw new IllegalArgumentException("Тип заявителя некорректный");
            }
        }
    }
}
