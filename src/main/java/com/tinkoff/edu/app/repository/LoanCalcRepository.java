package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.enums.LoanType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

import java.sql.Savepoint;
import java.util.UUID;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public interface LoanCalcRepository {

    LoanResponse save(LoanResponse response);

    void update(LoanResponse response);

    LoanResponse find(UUID uuid);


}
