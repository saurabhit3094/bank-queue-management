package com.assignment.service;

import com.assignment.config.RabbitMQConfigBuild;
import com.assignment.dto.TokenRequestDTO;
import com.assignment.exception.TokenException;
import com.assignment.model.Token;
import com.assignment.model.TokenServiceMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenServiceMapService tokenServiceMapService;

    @Autowired
    private AdminService adminService;

    @Autowired
    RabbitMQConfigBuild rabbitMQConfigBuild;

    @Test
    public void createTokenAndAssignToQueue() throws Exception {

        TokenRequestDTO dto = new TokenRequestDTO();
        dto.setAccountNumber(12345L);
        dto.setBranchId(111L);
        List<String> serviceList = Arrays.asList("SERVICE1", "SERVICE2", "SERVICE3", "SERVICE4", "SERVICE5");
        dto.setServicesList(serviceList);

        //rabbitMQConfigBuild.registerQueues();

        Token token = tokenService.createTokenAndAssignToQueue(dto);
        long tokenId = token.getTokenId();
        Token newToken = tokenService.getToken(tokenId);
        long newTokenId = newToken.getTokenId();
        Assert.assertEquals(newTokenId, tokenId);

       /* List<TokenServiceMap> tokenServiceMapList = tokenServiceMapService.getTokenServiceMapForTokenID(newTokenId);
        Assert.assertEquals(serviceList.size(),tokenServiceMapList.size());
        tokenServiceMapList.forEach(tokenServiceMap -> {
            Assert.assertTrue(serviceList.contains(tokenServiceMap.getServiceName()));
        });*/
    }



    @Test
    public void getToken()throws Exception {
        long tokenId =30;
        Token token = tokenService.getToken(tokenId);
        Assert.assertEquals(tokenId, token.getTokenId());
    }

    @Test
    public void markStatusCompleted()throws Exception {

        TokenRequestDTO dto = new TokenRequestDTO();
        dto.setAccountNumber(12345L);
        dto.setBranchId(111L);
        dto.setServicesList(Arrays.asList("SERVICE1"));

        Token token = tokenService.createTokenAndAssignToQueue(dto);
        long tokenId = token.getTokenId();

        tokenService.markStatusCompleted(tokenId);

        Token newToken = tokenService.getToken(tokenId);

        Assert.assertEquals("COMPLETED",newToken.getStatus());

    }

    @Test(expected = TokenException.class)
    public void checkCreateTokenIfCustomerNotRegister()throws TokenException,Exception{
        long accntNumber = 654422348L;
        TokenRequestDTO dto = new TokenRequestDTO();
        dto.setAccountNumber(accntNumber);
        dto.setBranchId(111L);
        List<String> serviceList = Arrays.asList("SERVICE1");
        dto.setServicesList(serviceList);
        Token token = tokenService.createTokenAndAssignToQueue(dto);
    }
}