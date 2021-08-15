package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;

/**
 * Created on 10.08.2021
 *
 * @author Elena Butakova
 *
 * Loan Calc Test
 *
 */
public class LoanCalcTest {
    public static void main(String... args) {
        int requestId = LoanCalcController.createRequest();
        System.out.println(requestId + " Должно быть 1");
    }
}
