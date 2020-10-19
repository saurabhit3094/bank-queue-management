package com.assignment.dao;

import com.assignment.model.Branch;
import com.assignment.model.Counter;
import com.assignment.model.Services;


public interface AdminDao {
    public Branch registerBranch(Branch branch)throws Exception;

    public Counter registerCounter(Counter counter)throws Exception;

    public Services registerServices(Services services)throws Exception;
}
