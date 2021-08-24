package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanRequest;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public class LoanResponse {
    private final LoanResponseType responseType;
    private final LoanRequest request;
    private final int id;

    public LoanResponse(LoanResponseType responseType, LoanRequest request, int id) {
        this.responseType = responseType;
        this.request = request;
        this.id = id;
    }

    public LoanResponseType getResponseType() {
        return responseType;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LoanResponse{" +
                "responseType=" + responseType +
                ", request=" + request +
                ", id=" + id +
                '}';
    }
}
