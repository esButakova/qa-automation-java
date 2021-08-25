package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.StaticVariableLoanCalcRepository;
import com.tinkoff.edu.app.service.IpNotFriendlyServiceStatic;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanCalcTestSuite {
    private LoanCalcController classic_controller;
    private LoanCalcController ip_controller;
    private LoanRequest ooo_request;
    private LoanRequest ip_request;
    private LoanRequest owner_months_request;

    @BeforeEach
    public void init() {
        classic_controller = new LoanCalcController(new StaticLoanCalcService(new StaticVariableLoanCalcRepository()));
        ip_controller = new LoanCalcController(new IpNotFriendlyServiceStatic(new StaticVariableLoanCalcRepository()));
        ooo_request = new LoanRequest(LoanType.ooo, 1, 30, 3000);
        ip_request = new LoanRequest(LoanType.ip, 1, 30, 3000);
        owner_months_request = new LoanRequest(LoanType.person, 2, 70, 3000);
    }

    @Test
    public void shouldGetApproveForOOO() {
        LoanResponse response = classic_controller.createRequest(ooo_request);
        assertTrue(response.getId() > 0);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    public void shouldGetApproveForIP() {
        LoanResponse response = classic_controller.createRequest(ip_request);
        assertTrue(response.getId() > 0);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    public void shouldGetDeniedByMonths() {
        LoanResponse response = classic_controller.createRequest(owner_months_request);
        assertEquals(-1, response.getId());
    }

    @Test
    public void shouldGetIPNotFriendlyError() {
        LoanResponse response = ip_controller.createRequest(ip_request);
        assertEquals(-1, response.getId());
    }

    @Test
    public void shouldGetIncrementedIdWhenAnyCall() {
        int currentId;
        currentId = StaticVariableLoanCalcRepository.getRequestId();
        LoanResponse response = ip_controller.createRequest(ooo_request);
        assertTrue(response.getId() > currentId);
    }
}

