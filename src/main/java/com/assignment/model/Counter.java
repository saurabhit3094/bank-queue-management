package com.assignment.model;

import javax.persistence.*;

@Entity
@Table(name = "counter")
public class Counter {

    @Id
    @Column(name = "counter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long counterId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "branch_id")
    private long branchId;

    @Column(name = "active_token_id")
    private Long activeTokenId;

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public Long getActiveTokenId() {
        return activeTokenId;
    }

    public void setActiveTokenId(Long activeTokenId) {
        this.activeTokenId = activeTokenId;
    }
}
