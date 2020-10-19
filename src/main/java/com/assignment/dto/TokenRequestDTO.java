package com.assignment.dto;

import java.util.List;

public class TokenRequestDTO {

    private Long accountNumber;
    private Long branchId;
    private List<String> servicesList;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }


    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
        this.servicesList = servicesList;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
