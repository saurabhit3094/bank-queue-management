package com.assignment.service;

import com.assignment.dto.NextStepInputDTO;
import com.assignment.enums.CustomerType;
import com.assignment.model.Counter;
import com.assignment.model.Token;
import com.assignment.dao.CounterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    CounterDao counterDao;

    @Autowired
    TokenService tokenService;

    @Autowired
    TokenServiceMapService tokenServiceMapService;

    @Override
    public List<Counter> getAll() throws Exception {
        return counterDao.getAll();
    }

    @Override
    public Counter getCounter(long counterId) throws Exception {
        return counterDao.getCounter(counterId);
    }

    @Override
    public void moveToNextStep(NextStepInputDTO nextStepInputDTO) throws Exception {

        Token token = tokenService.getToken(nextStepInputDTO.getTokenId());
        Counter counter =  counterDao.getCounter(nextStepInputDTO.getCounterId());
        //queue token for next service .
        tokenService.queueToken(token,nextStepInputDTO.getNextServiceName(), CustomerType.valueOf(counter.getCustomerType()));  //CustomerType.valueOf(counter.getCustomerType())
        //change status of served service and change isAnyServiceActive TO N .
        tokenServiceMapService.markCurrentServiceCompleted(token.getTokenId(),counter.getServiceName(),counter.getCustomerType(),counter.getBranchId());
        //change active token status in counter table
        updateActiveToken(0,nextStepInputDTO.getCounterId());
    }

    @Override
    public void updateActiveToken(long tokenId, long counterId) throws Exception {
        counterDao.updateActiveToken(tokenId, counterId);
    }

    @Override
    public List<Counter> getCounterForBranch(long branchId) throws Exception {
        return counterDao.getCounterForBranch(branchId);
    }
}
