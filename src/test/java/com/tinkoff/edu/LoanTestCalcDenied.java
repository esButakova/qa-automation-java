package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
import com.tinkoff.edu.app.service.IpNotFriendlyServiceStatic;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTestCalcDenied {
    @Test
    public void LoanTestCalc_Denied_By_Month() {
        LoanCalcController controller = new LoanCalcController(new StaticLoanCalcService(new StaticVariableLoanCalcRepository()));

        LoanRequest request = new LoanRequest(LoanType.ooo, 2, 70, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    public void LoanTestCalc_Denied_By_Loan_Type() {
        LoanCalcController controller = new LoanCalcController(new IpNotFriendlyServiceStatic(new StaticVariableLoanCalcRepository()));
        LoanRequest request = new LoanRequest(LoanType.ip, 2, 2, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(-1, response.getId());
    }
}

