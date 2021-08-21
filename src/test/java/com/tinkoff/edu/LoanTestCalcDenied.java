package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.LoanCalcController.createRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTestCalcDenied {
    @Test
    public void LoanTestCalc_Denied() {
        LoanRequest request = new LoanRequest(LoanType.ip, 2, 70, 3000);
        int requestId = createRequest(request);
        assertEquals(-1, requestId);
    }
}

