package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
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

