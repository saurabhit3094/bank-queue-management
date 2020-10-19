package com.assignment.model;

import com.assignment.enums.CustomerType;

import javax.persistence.*;

@Entity
@Table(name = "token_service_map")
public class TokenServiceMap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token_id")
    private long tokenId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "is_completed")
    private char isCompleted;

    @Column(name = "is_any_service_active")
    private char isAnyServiceActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @Column(name = "branch_id")
    private Long branchId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public char getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(char isCompleted) {
        this.isCompleted = isCompleted;
    }

    public char getIsAnyServiceActive() {
        return isAnyServiceActive;
    }

    public void setIsAnyServiceActive(char isAnyServiceActive) {
        this.isAnyServiceActive = isAnyServiceActive;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}
