package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTestCalcApprove {
    @Test
    public void LoanTestCalc_Approve() {
        LoanCalcController controller = new LoanCalcController(new StaticLoanCalcService(new StaticVariableLoanCalcRepository()));
        LoanRequest request = new LoanRequest(LoanType.ooo, 1, 30, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(1, response.getId());
    }
}

