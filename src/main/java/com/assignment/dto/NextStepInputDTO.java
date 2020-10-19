package com.assignment.dto;

public class NextStepInputDTO {

    private long tokenId;

    private long counterId;

    private String nextServiceName;

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    public String getNextServiceName() {
        return nextServiceName;
    }

    public void setNextServiceName(String nextServiceName) {
        this.nextServiceName = nextServiceName;
    }
}
