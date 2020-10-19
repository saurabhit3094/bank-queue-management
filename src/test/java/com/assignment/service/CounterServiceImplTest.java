package com.assignment.service;

import com.assignment.enums.CustomerType;
import com.assignment.model.Counter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CounterServiceImplTest {

    @Autowired
    AdminService adminService;

    @Autowired
    CounterService counterService;

    @Test
    public void getCounter()throws Exception {
        Counter counter = new Counter();
        counter.setServiceName("LOAN");
        counter.setBranchId(1);
        counter.setCustomerType("REGULAR");
        Counter retCounter = adminService.registerCounter(counter);
        Counter newCounter = counterService.getCounter(retCounter.getCounterId());
        Assert.assertEquals(retCounter.getCounterId(),newCounter.getCounterId());
        Assert.assertEquals(retCounter.getBranchId(),newCounter.getBranchId());
        Assert.assertEquals(retCounter.getCustomerType(),newCounter.getCustomerType());
        Assert.assertEquals(retCounter.getServiceName(),newCounter.getServiceName());
    }

    @Test
    public void updateActiveToken()throws Exception {
        Counter counter = new Counter();
        counter.setServiceName("BANKING");
        counter.setBranchId(1);
        counter.setCustomerType("PREMIUM");
        Counter retCounter = adminService.registerCounter(counter);
        long tokenId = 21L;
        counterService.updateActiveToken(tokenId,retCounter.getCounterId());
        Counter newCounter = counterService.getCounter(retCounter.getCounterId());
        Assert.assertEquals(tokenId,(long)newCounter.getActiveTokenId());

    }
}