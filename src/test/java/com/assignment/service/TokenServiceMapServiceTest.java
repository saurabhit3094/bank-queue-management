package com.assignment.service;

import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.TokenServiceMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenServiceMapServiceTest {

    @Autowired
    TokenServiceMapService tokenServiceMapService;

    @Test
    public void nextToken()throws Exception {
        String serviceName = "BANKING";
        CustomerType customerType = CustomerType.REGULAR;
        long tokenId = tokenServiceMapService.nextToken(serviceName, customerType);
        List<TokenServiceMap> tokenServiceMapList = tokenServiceMapService.getTokenServiceMapForTokenID(tokenId);
        tokenServiceMapList.forEach(tokenServiceMap -> {
            if (tokenServiceMap.getServiceName().equals(serviceName) && tokenServiceMap.getCustomerType().equals(customerType)){
                Assert.assertEquals('Y',tokenServiceMap.getIsAnyServiceActive());
            }
        });
    }

    @Test
    public void isAnyPendingService()throws Exception {
        long tokenId = 26L;
        long branchId = 1L;
        List<TokenServiceMap> tokenServiceMaps = tokenServiceMapService.isAnyPendingService(tokenId,branchId);
        tokenServiceMaps.forEach(tokenServiceMap -> {
            Assert.assertEquals('N',tokenServiceMap.getIsCompleted());
        });
    }

    @Test(expected = TokenException.class)
    public void checkWhenNoTokenExists()throws TokenException,Exception {
        tokenServiceMapService.nextToken("NEW-SERVICE",CustomerType.REGULAR);
    }
}