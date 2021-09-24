package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;

import java.util.Objects;
import java.util.UUID;

/**
 * Created on 23.08.2021
 *
 * @author Elena Butakova
 */
public class LoanResponse{
    private LoanResponseType responseType;
    private final LoanRequest request;
    private final UUID id;

    public LoanResponse() {
        this(null, null, null);
    }

    public LoanResponse(LoanResponseType responseType, LoanRequest request, UUID id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanResponse)) return false;
        LoanResponse response = (LoanResponse) o;
        return responseType == response.responseType && request.equals(response.request) && id.equals(response.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseType, request, id);
    }
}
