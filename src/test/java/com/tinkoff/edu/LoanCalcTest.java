package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static com.tinkoff.edu.app.enums.LoanType.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoanCalcTest {
    private LoanCalcController calcController;
    private LoanRequest request;

    @BeforeEach
    public void init() {
        calcController = new LoanCalcController(new StaticLoanCalcService(new VariableLoanCalcRepository()));
        request = new LoanRequest(person, 10, 1000);
    }

    @Test
    @DisplayName("requestId = 1 после первого запроса")
    public void shouldGetOneWhenFirstResponse() {
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("requestId увеличивается на 1")
    public void shouldGetIncrementedIdAnyCall() {
        int firstCall = calcController.createRequest(request).getId();
        int secondCall = calcController.createRequest(request).getId();
        assertEquals(firstCall + 1, secondCall);
    }

    @Test
    @DisplayName("Ошибка, если запрос null")
    public void shouldGetErrorWhenApplyNullRequest(){
        LoanResponse response = calcController.createRequest(null);
        assertEquals(-1, response.getId());
    }

    @Test
    @DisplayName("Ошибка, если сумма = 0")
    public void shouldGetErrorWhenApplyZeroAmountRequest(){
        request = new LoanRequest(person, 10, 0);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    @DisplayName("Ошибка, при отрицательной сумме")
    public void shouldGetErrorWhenApplyNegativeAmountRequest(){
        request = new LoanRequest(person, 10, -10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    @DisplayName("Ошибка, количестве месяцев = 0")
    public void shouldGetErrorWhenApplyZeroMonthsRequest(){
        request = new LoanRequest(ooo, 0, 12);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    @DisplayName("Ошибка, если количество месяцев отрицательное число")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest(){
        request = new LoanRequest(ooo, -1, 12);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(-1, response.getId());
    }

    @Test
    @DisplayName("APPROVED для  person, amount=10_000, months=12")
    public void shouldGetApproveWhenPersonLess10000Less12Corner(){
        request = new LoanRequest(person, 12, 10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("APPROVED для person, amount<10_000, months<12")
    public void shouldGetApproveWhenPersonLess10000Less(){
        request = new LoanRequest(person, 11, 9_999);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для person, amount>10_000, months>12")
    public void shouldGetDeclineWhenPersonMore10000More12(){
        request = new LoanRequest(person, 13, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для Person, amount>10_000, months<=12")
    public void shouldGetDeclinePersonMore10000Less12(){
        request = new LoanRequest(person, 12, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для Person, amount<10_000, months>=12")
    public void shouldGetDeclinePersonLess10000More12(){
        request = new LoanRequest(person, 12, 9_999);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для OOO, amount<=10_000, any months")
    public void shouldGetDeclineWhenOOOLess10000(){
        request = new LoanRequest(ooo, 1, 10_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("APPROVED для OOO, amount>10_000, months<12")
    public void shouldGetApproveWhenOOOMore10000Less12(){
        request = new LoanRequest(ooo, 11, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для OOO, amount>10_000, months>=12")
    public void shouldGetDeclineWhenOOOMore10000More12(){
        request = new LoanRequest(ooo, 12, 10_001);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }

    @Test
    @DisplayName("DENIED для IP, any amount, any months")
    public void shouldGetDeclineWhenIP(){
        request = new LoanRequest(ip, 5, 5_000);
        LoanResponse response = calcController.createRequest(request);
        assertEquals(1, response.getId());
    }
}