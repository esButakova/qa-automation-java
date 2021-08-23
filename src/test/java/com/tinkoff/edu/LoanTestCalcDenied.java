package com.tinkoff.edu;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;
import com.tinkoff.edu.app.LoanType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanTestCalcDenied {
    @Test
    public void LoanTestCalc_Denied() {
        LoanCalcController controller = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.ip, 2, 70, 3000);
        LoanResponse response = controller.createRequest(request);
        assertEquals(-1, response.getId());
    }
}

