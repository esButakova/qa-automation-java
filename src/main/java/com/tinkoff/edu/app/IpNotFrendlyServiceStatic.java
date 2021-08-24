package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public class IpNotFrendlyServiceStatic extends StaticLoanCalcService {
    public IpNotFrendlyServiceStatic(LoanCalcRepository loanCalcRepositry) {
        super(loanCalcRepositry);
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
