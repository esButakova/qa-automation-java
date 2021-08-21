package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;

import static com.tinkoff.edu.app.LoanCalcController.createRequest;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 *
 * Тест для кредитного калькулятора
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request=new LoanRequest(LoanType.ip,1,30,3000);
        int requestId = createRequest(request);
        System.out.println(requestId + " Должно быть 1");
    }
}
