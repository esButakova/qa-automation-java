package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 *
 * Тест для кредитного калькулятора
 */
public class LoanCalcTest {
    public static void main(String... args) {
        LoanRequest request = new LoanRequest(LoanType.ip, 1, 30, 3000);
        LoanCalcController controller = new LoanCalcController(new StaticVariableLoanCalcRepository());
        LoanResponse response = controller.createRequest(request);
        System.out.println(response);
    }
}
