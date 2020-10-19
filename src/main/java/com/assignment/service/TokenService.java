package com.assignment.service;

import com.assignment.dto.TokenRequestDTO;
import com.assignment.enums.CustomerType;
import com.assignment.model.Token;

public interface TokenService {

    //public Token saveToken(Token token) throws Exception;

    public Token createTokenAndAssignToQueue(TokenRequestDTO tokenRequestDTO)throws Exception;

    public void queueToken(Token token, String service,CustomerType customerType)throws Exception;

    public Token getToken(long tokenId)throws Exception;

    public void markStatusCompleted(long tokenId)throws Exception;
}
