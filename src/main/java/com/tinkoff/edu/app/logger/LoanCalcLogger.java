package com.tinkoff.edu.app.logger;

import com.tinkoff.edu.app.model.LoanRequest;

/**
 * Created on 15.08.2021
 *
 * @author Elena Butakova
 */
public class LoanCalcLogger {
    public static void log(String eventType, LoanRequest request) {
        System.out.println(eventType + ":");
        System.out.println(request);
    }

    public static void log(String eventType, String message) {
        System.out.println(eventType + ": " + message);
    }
}
