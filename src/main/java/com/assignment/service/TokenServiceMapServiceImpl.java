package com.assignment.service;

import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.TokenServiceMap;
import com.assignment.dao.TokenServiceMapDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceMapServiceImpl implements TokenServiceMapService
{
    @Autowired
    TokenServiceMapDao tokenServiceMapDao;

    @Override
    public TokenServiceMap save(TokenServiceMap tokenServiceMap) throws Exception {
        return tokenServiceMapDao.save(tokenServiceMap);
    }

    @Override
    public long nextToken(String serviceName, CustomerType customerType) throws TokenException,Exception {
        long tokenId = tokenServiceMapDao.nextToken(serviceName,customerType).getTokenId();
        //update isAnyserviceActive to Y ... to avoid same token to be picked for another service.
        tokenServiceMapDao.updateIsServiceActive('Y',tokenId);

        return tokenId;
    }

    @Override
    public void markCurrentServiceCompleted(long tokenId, String serviceName, String customerType, long branchId) throws Exception {
        tokenServiceMapDao.markCurrentServiceCompleted(tokenId, serviceName, branchId);
    }

    @Override
    public List<TokenServiceMap> isAnyPendingService(long tokenId, long branchId) throws Exception {
        return tokenServiceMapDao.isAnyPendingService(tokenId, branchId);
    }

    @Override
    public List<TokenServiceMap> getTokenServiceMapForTokenID(long tokenId) throws Exception {
        return tokenServiceMapDao.getTokenServiceMapForTokenID(tokenId);
    }

    @Override
    public List<TokenServiceMap> getAllPendingTokenList(long branchId) throws TokenException,Exception {
        return tokenServiceMapDao.getAllPendingTokenList(branchId);
    }


}
