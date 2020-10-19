package com.assignment.dao;

import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.TokenServiceMap;

import java.util.List;

public interface TokenServiceMapDao {

    public TokenServiceMap save(TokenServiceMap tokenServiceMap)throws Exception;

    public TokenServiceMap nextToken(String serviceName, CustomerType customerType)throws Exception;

    public void updateIsServiceActive(char isServiceActive,long tokenId)throws Exception;

    public void markCurrentServiceCompleted(long tokenId, String serviceName, long branchId);

    public List<TokenServiceMap> isAnyPendingService(long tokenId, long branchId)throws Exception;

    public List<TokenServiceMap> getTokenServiceMapForTokenID(long tokenId)throws Exception;

    public List<TokenServiceMap> getAllPendingTokenList(long branchId)throws TokenException,Exception;
}
