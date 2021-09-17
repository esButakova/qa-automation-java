package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.constants.ValidatorConstants;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.exception.LoanServiceException;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.util.List;
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
            LoanResponse loan = new LoanResponse(loanStatus, request, UUID.randomUUID());
            return loanCalcRepository.save(loan);
        } catch (IllegalArgumentException e) {
            throw new LoanServiceException("Ошибка валидации запроса", e);
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

    @Override
    public List<LoanResponse> findByType(LoanType type) {
        return loanCalcRepository.findByType(type);
    }

    private void validateRequest(LoanRequest request) {
        String validationResult = getRequestValidationMessage(request);
        if (validationResult != null) {
            throw new IllegalArgumentException(validationResult);
        }
    }

    private String getRequestValidationMessage(LoanRequest request) {
        if (request == null) {
            return ValidatorConstants.ERROR_NULL_REQUEST;
        }

        if (request.getFullName() == null || !isFullNameValid(request.getFullName())) {
            return ValidatorConstants.ERROR_FULLNAME_REQUEST;
        }

        if (request.getAmount() < 0.01 || request.getAmount() > 999_999.99) {
            return ValidatorConstants.ERROR_AMOUNT_REQUEST;
        }
        if (request.getMonths() < 1 || request.getMonths() > 100) {
            return ValidatorConstants.ERROR_MONTH_REQUEST;
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
            case PERSON: {

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

            case OOO: {
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

            case IP: {
                return DENIED;
            }

            default: {
                throw new IllegalArgumentException("Тип заявителя некорректный");
            }
        }
    }
}
