package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.IpNotFriendlyServiceStatic;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoanCalcTest {
    private LoanCalcController classic_controller;
    private LoanCalcController ip_controller;
    private LoanRequest ip_request;
    private LoanRequest person_denied_request;
    private LoanRequest person_request_12months;
    private LoanRequest ooo_request_max_amount;
    private LoanRequest ooo_approved_request;
    private LoanRequest ooo_request_max_months;
    private LoanRequest person_request_max_amont;
    private LoanRequest person_request_min_amont;
    private LoanRequest person_request_min_mounths;
    private LoanRequest ooo_request_9amount;
    private LoanRequest ooo_request_min_months;
    private LoanRequest ooo_request_min_amount;
    private LoanRequest none_request;
    private LoanRequest ooo_request_min;
    private LoanRequest ooo_request_max;
    private LoanRequest null_request;
    private LoanRequest null__request;


    @BeforeEach
    public void initData() {
        classic_controller = new LoanCalcController(new StaticLoanCalcService(new VariableLoanCalcRepository()));
        ip_controller = new LoanCalcController(new IpNotFriendlyServiceStatic(new VariableLoanCalcRepository()));
        ip_request = new LoanRequest(LoanType.ip, 1, 30, 3000);
        person_denied_request = new LoanRequest(LoanType.person, 2, 70, 3000);
        person_request_12months = new LoanRequest(LoanType.person, 3, 12, 3000);
        person_request_max_amont = new LoanRequest(LoanType.person, 3, 12, 11000);
        person_request_min_amont = new LoanRequest(LoanType.person, 3, 12, 0);
        person_request_min_mounths = new LoanRequest(LoanType.person, 3, 0, 10000);
        ooo_request_max_amount = new LoanRequest(LoanType.ooo, 1, 0, 10000);
        ooo_request_9amount = new LoanRequest(LoanType.ooo, 1, 30, 10000);
        ooo_approved_request = new LoanRequest(LoanType.ooo, 1, 11, 11000);
        ooo_request_max_months = new LoanRequest(LoanType.ooo, 4, 12, 11000);
        ooo_request_min_months = new LoanRequest(LoanType.ooo, 4, 0, 11000);
        ooo_request_min_amount = new LoanRequest(LoanType.ooo, 1, 10, 0);
        ooo_request_min = new LoanRequest(LoanType.ooo, 1, 0, 0);
        ooo_request_max = new LoanRequest(LoanType.ooo, 1, 12, 10000);
        none_request = new LoanRequest(LoanType.none, 1, 10, 0);
        null_request = new LoanRequest(LoanType.ooo, -1, -1, -1);
    }


    @Test
    void shouldGetApproveForPerson12Month() {
        LoanResponse response = classic_controller.createRequest(person_request_12months);
        assertTrue(response.getId() > 0);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());

    }


    @Test
    void shouldGetDeniedForPersonMaxAmount() {
        LoanResponse response = classic_controller.createRequest(person_request_max_amont);
        assertEquals(-1, response.getId());
    }

    @Test
    void shouldGetDeniedForPersonMinAmount() {
        LoanResponse response = classic_controller.createRequest(person_request_min_amont);
        assertEquals(-1, response.getId());
    }


    @Test
    void shouldGetDeniedForPersonForMounth() {
        LoanResponse response = classic_controller.createRequest(person_denied_request);
        assertEquals(-1, response.getId());
    }

    @Test
    void shouldGetDeniedForPersonMinMounth() {
        LoanResponse response = classic_controller.createRequest(person_request_min_mounths);
        assertEquals(-1, response.getId());
    }


    @Test
    void shouldGetDeniedForOOOForMaxAmount() {
        LoanResponse response = classic_controller.createRequest(ooo_request_max_amount);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForOOOFor9Amount() {
        LoanResponse response = classic_controller.createRequest(ooo_request_9amount);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }


    @Test
    void shouldGetApproveForOOO() {
        LoanResponse response = classic_controller.createRequest(ooo_approved_request);
        assertTrue(response.getId() > 0);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForOOOForMonth() {
        LoanResponse response = classic_controller.createRequest(ooo_request_max_months);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForOOOForMinMonth() {
        LoanResponse response = classic_controller.createRequest(ooo_request_min_months);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForOOOForMinAmounth() {
        LoanResponse response = classic_controller.createRequest(ooo_request_min_amount);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForOOOnull() {
        LoanResponse response = classic_controller.createRequest(null_request);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForMin() {
        LoanResponse response = classic_controller.createRequest(ooo_request_min);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForMax() {
        LoanResponse response = classic_controller.createRequest(ooo_request_max);
        assertTrue(response.getId() == -1);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    void shouldGetDeniedForIP() {
        LoanResponse response = classic_controller.createRequest(ip_request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }


    @Test
    void shouldGetIPNotFriendlyError() {
        LoanResponse response = ip_controller.createRequest(ip_request);
        assertEquals(-1, response.getId());
    }

    @Test
    void shouldGetIncrementedIdWhenAnyCall() {
        int currentId;
        currentId = VariableLoanCalcRepository.getRequestId();
        LoanResponse response = ip_controller.createRequest(ooo_approved_request);
        assertTrue(response.getId() > currentId);
    }

    @Test
    void shouldGetDeniedForNone() {
        LoanResponse response = classic_controller.createRequest(none_request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

}

