package com.assignment.service;

import com.assignment.dto.NextStepInputDTO;
import com.assignment.model.Counter;

import java.util.List;

public interface CounterService {
    public List<Counter> getAll()throws Exception;

    public Counter getCounter(long counterId)throws Exception;

    public void moveToNextStep(NextStepInputDTO nextStepInputDTO)throws Exception;

    public void updateActiveToken(long tokenId, long counterId)throws Exception;

    public List<Counter> getCounterForBranch(long branchId)throws Exception;
}
