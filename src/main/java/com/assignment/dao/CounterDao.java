package com.assignment.dao;

import com.assignment.model.Counter;

import java.util.List;

public interface CounterDao {

    public List<Counter> getAll() throws Exception;

    public Counter getCounter(long counterId)throws Exception;

    public void updateActiveToken(long tokenId, long counterId)throws Exception;

    public List<Counter> getCounterForBranch(long branchId)throws Exception;
}
