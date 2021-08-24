package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;

import static com.tinkoff.edu.app.logger.LoanCalcLogger.log;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public class IpNotFriendlyServiceStatic extends StaticLoanCalcService {
    public IpNotFriendlyServiceStatic(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public LoanResponse createRequest(LoanRequest request) {
        if (request.getType().equals(LoanType.ip)) {
            log("Ошибка", "IpNotFrendlyService");
            return new LoanResponse(LoanResponseType.DENIED, request, -1);
        }
        return super.createRequest(request);
    }
}
