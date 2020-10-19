package com.assignment.dto;

import com.assignment.enums.CustomerType;
import com.assignment.model.Token;

import java.io.Serializable;

public class QueueDTO implements Serializable {

    private static final long serialVersionUID = -6097704023487570726L;

    private Token token;

    private String serviceName;

    private CustomerType customerType;

    private long branchId;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return String.format("{\"token\": %s, \"serviceName\": \"%s\", \"customerType\": %s}", token, serviceName, customerType);
    }
}
