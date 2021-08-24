package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTestCalcDenied {
    @Test
    public void LoanTestCalc_Denied_By_Month() {
        LoanCalcRepository repository = new StaticVariableLoanCalcRepository();
        LoanCalcController controller = new LoanCalcController(repository);
        LoanRequest request = new LoanRequest(LoanType.ooo, 2, 70, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    public void LoanTestCalc_Denied_By_Loan_Type() {
        LoanCalcRepository repository = new StaticVariableLoanCalcRepository();
        LoanCalcController controller = new LoanCalcController(repository);
        LoanRequest request = new LoanRequest(LoanType.ip, 2, 2, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(-1, response.getId());
    }
}

