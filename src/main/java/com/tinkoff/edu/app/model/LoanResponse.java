package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;

import java.util.UUID;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public class LoanResponse {
    private LoanResponseType responseType;
    private final LoanRequest request;
    private final UUID id;

    public LoanResponse(LoanResponseType responseType, LoanRequest request) {
        this.responseType = responseType;
        this.request = request;
        this.id = UUID.randomUUID();
    }

    public LoanResponseType getResponseType() {
        return responseType;
    }

    public LoanRequest getRequest() {
        return request;
    }

    public UUID getId() {
        return id;
    }

    public void setResponseType(LoanResponseType responseType) {
        this.responseType = responseType;
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
