package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTestCalcApprove {
    @Test
    public void LoanTestCalc_Approve() {
        LoanCalcController controller = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.ip, 1, 30, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(1, response.getId());
    }
}

