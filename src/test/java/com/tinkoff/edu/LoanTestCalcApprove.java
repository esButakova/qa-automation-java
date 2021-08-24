package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTestCalcApprove {
    @Test
    public void LoanTestCalc_Approve() {
        LoanCalcRepository repository = new StaticVariableLoanCalcRepository();
        LoanCalcController controller = new LoanCalcController(repository);
        LoanRequest request = new LoanRequest(LoanType.ooo, 1, 30, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(1, response.getId());
    }
}

