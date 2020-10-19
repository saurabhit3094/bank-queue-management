package com.assignment.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "token")
public class Token implements Serializable{


    private static final long serialVersionUID = -6097704049487570726L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long tokenId;

    @Column(name = "customer_acc_num")
    private long customerAccNum;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private Date createDate;

    @Column(name = "branch_id")
    private long branchId;

    @Column(name = "status")
    private String status;


    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public long getCustomerAccNum() {
        return customerAccNum;
    }

    public void setCustomerAccNum(long customerAccNum) {
        this.customerAccNum = customerAccNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("{\"tokenId\": \"%s\", \"accountNumber\": \"%s\", \"branchId\": \"%s\"}", tokenId, customerAccNum, branchId);
    }
}
