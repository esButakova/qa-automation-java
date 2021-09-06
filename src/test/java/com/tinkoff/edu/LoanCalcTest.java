package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.exception.LoanServiceException;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.tinkoff.edu.app.enums.LoanType.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoanCalcTest {
    private LoanCalcController calcController;
    private LoanRequest request;

    @BeforeEach
    public void init() {
        calcController = new LoanCalcController(new StaticLoanCalcService(new VariableLoanCalcRepository()));
        request = new LoanRequest(person, 10, 1000, "Василь-Петрович");
    }

    @Test
    @DisplayName("requestId = не пустой после первого запроса")
    public void shouldGetOneWhenFirstResponse() {
        LoanResponse response = calcController.createRequest(request);
        assertNotNull(response.getId());
    }

    @Test
    @DisplayName("Проверка ID на уникальность")
    public void shouldGetIncrementedIdAnyCall() {
        UUID firstCallId = calcController.createRequest(request).getId();
        UUID secondCallId = calcController.createRequest(request).getId();
        assertNotEquals(firstCallId, secondCallId);
    }

    @Test
    @DisplayName("Ошибка, если запрос null")
    public void shouldGetErrorWhenApplyNullRequest() {
        // LoanResponse response = calcController.createRequest(null);
        //assertNull(response);
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(null));
    }

    @Test
    @DisplayName("Ошибка, если сумма = 0")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        request = new LoanRequest(person, 10, 0, "Петро-Сергеич");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка при отрицательной сумме")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        request = new LoanRequest(person, 10, -10_000, "Михайло-Андреич");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, количестве месяцев = 0")
    public void shouldGetErrorWhenApplyZeroMonthsRequest() {
        request = new LoanRequest(ooo, 0, 12, "Криптофонд");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, если количество месяцев - отрицательное число")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest() {
        request = new LoanRequest(ooo, -1, 12, "Самса-и-Партнеры");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("APPROVED для  person, amount=10_000, months=12")
    public void shouldGetApproveWhenPersonLess10000Less12Corner() {
        request = new LoanRequest(person, 12, 10_000, "Сильвестр-Петрович");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    @DisplayName("APPROVED для person, amount<10_000, months<12")
    public void shouldGetApproveWhenPersonLess10000Less() {
        request = new LoanRequest(person, 11, 9_999, "Джон-Зигмундович");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для person, amount>10_000, months>12")
    public void shouldGetDeclineWhenPersonMore10000More12() {
        request = new LoanRequest(person, 13, 10_001, "Обналичка-в-Чертаново");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для Person, amount>10_000, months<=12")
    public void shouldGetDeclinePersonMore10000Less12() {
        request = new LoanRequest(person, 12, 10_001, "Мик-Джаггерович");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для Person, amount<10_000, months>12")
    public void shouldGetDeclinePersonLess10000More12() {
        request = new LoanRequest(person, 13, 9_999, "Сильвестр-Петрович");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для OOO, amount<=10_000, any months")
    public void shouldGetDeclineWhenOOOLess10000() {
        request = new LoanRequest(ooo, 1, 10_000, "Фонд-биткойна");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    @DisplayName("APPROVED для OOO, amount>10_000, months<12")
    public void shouldGetApproveWhenOOOMore10000Less12() {
        request = new LoanRequest(ooo, 11, 10_001, "Криптофонд");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для OOO, amount>10_000, months>=12")
    public void shouldGetDeclineWhenOOOMore10000More12() {
        request = new LoanRequest(ooo, 12, 10_001, "Обменник-в-Люберцах");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }

    @Test
    @DisplayName("DENIED для IP, any amount, any months")
    public void shouldGetDeclineWhenIP() {
        request = new LoanRequest(ip, 5, 5_000, "Надя-Ноготочки");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }


    @Test
    @DisplayName("Проверка запроса статуса по UUID ")
    public void shouldGetLoanStatusForUUID() {
        request = new LoanRequest(ooo, 11, 10_001, "Криптофонд");
        LoanResponse response = calcController.createRequest(request);
        UUID uuid = response.getId();
        LoanResponseType loanType = calcController.getLoanStatus(uuid);
        assertEquals(LoanResponseType.APPROVED, loanType);
    }

    @Test
    @DisplayName("Проверка запроса статуса по несуществующему UUID ")
    public void shouldGetNullByNewUUID() {
        calcController.createRequest(request);
        UUID uuid = UUID.randomUUID();
        LoanResponseType loanType = calcController.getLoanStatus(uuid);
        assertNull(loanType);
    }


    @Test
    @DisplayName("Обновление статуса по UUID ")
    public void shouldUpdateLoanStatusByUUID() {
        request = new LoanRequest(ooo, 11, 10_001, "Криптофонд");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.APPROVED, response.getResponseType());
        UUID uuid = response.getId();
        LoanResponseType loanType = calcController.updateLoanStatus(uuid, LoanResponseType.DENIED);
        assertEquals(LoanResponseType.DENIED, loanType);
    }

    @Test
    @DisplayName("Обновление статуса по несуществующему UUID ")
    public void shouldReturnNullWhenUpdateByNewUUID() {
        calcController.createRequest(request);
        UUID uuid = UUID.randomUUID();
        LoanResponseType loanType = calcController.updateLoanStatus(uuid, LoanResponseType.APPROVED);
        assertNull(loanType);
    }

    @Test
    @DisplayName("Ошибка, если имя меньше 10")
    public void shouldGetErrorWhenSmallName() {
        request = new LoanRequest(person, 11, 9_999, "Петрo");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, если имя больше 100")
    public void shouldGetErrorWhenBigName() {
        request = new LoanRequest(person, 11, 9_999, "ПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроПетроП");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, если имя больше содержит число")
    public void shouldGetErrorWhenNumberInName() {
        request = new LoanRequest(person, 11, 9_999, "Петрович1");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, если имя больше содержит спец символы")
    public void shouldGetErrorWhenSymbolName() {
        request = new LoanRequest(person, 11, 9_999, "Петрович%");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, если передать пустой тип")
    public void shouldGetErrorWhenTypeNone() {
        request = new LoanRequest(none, 11, 10_001, "Петровичxx");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Отказ, если сумма = 0.001")
    public void shouldGetErrorWhenApply0_001AmountRequest() {
        request = new LoanRequest(person, 10, (int) 0.001, "Петро-Сергеич");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Отказ, если сумма = 1000000")
    public void shouldGetErrorWhenApply1000000AmountRequest() {
        request = new LoanRequest(person, 10, 1000000, "ПетроСергеич");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, месяц меньше 1 ")
    public void shouldGetErrorWhenMonth0_5Name() {
        request = new LoanRequest(person, (int) 0.5, 9_999, "ПетроСергеичЛи");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("Ошибка, месяц больше 100")
    public void shouldGetErrorWhenMonth100Name() {
        request = new LoanRequest(person, 101, 9_999, "ПетроСергеич-Ли");
        assertThrows(LoanServiceException.class, () -> calcController.createRequest(request));
    }

    @Test
    @DisplayName("DENIED, запрос на 99 месяцев")
    public void shouldGetDeniedWhenMonth99() {
        request = new LoanRequest(person, 99, 9_999, "Петро-СергеичЛи");
        LoanResponse response = calcController.createRequest(request);
        assertEquals(LoanResponseType.DENIED, response.getResponseType());
    }


}