package com.tinkoff.edu.app;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public interface LoanCalcRepository {
    LoanResponse save(LoanRequest request);
}
