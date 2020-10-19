package com.assignment.service;

import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.Token;
import com.assignment.model.TokenServiceMap;

import java.util.List;

public interface TokenServiceMapService {
    public TokenServiceMap save(TokenServiceMap tokenServiceMap)throws Exception;

    public long nextToken(String serviceName, CustomerType customerType) throws Exception;

    public void markCurrentServiceCompleted(long tokenId, String serviceName, String customerType, long branchId)throws  Exception;

    public List<TokenServiceMap> isAnyPendingService(long tokenId, long branchId)throws Exception;

    public List<TokenServiceMap> getTokenServiceMapForTokenID(long tokenId)throws Exception;

    public List<TokenServiceMap> getAllPendingTokenList(long branchId)throws TokenException,Exception;
}
