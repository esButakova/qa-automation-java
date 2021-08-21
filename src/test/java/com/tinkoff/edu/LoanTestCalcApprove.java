package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.LoanCalcController.createRequest;
import static org.junit.jupiter.api.Assertions.*;

public class LoanTestCalcApprove {
    @Test
    public void LoanTestCalc_Approve() {
        LoanRequest request = new LoanRequest(LoanType.ip, 1, 30, 3000);
        int requestId = createRequest(request);
        assertEquals(1, requestId);
    }
}

