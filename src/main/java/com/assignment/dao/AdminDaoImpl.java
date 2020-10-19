package com.assignment.dao;

import com.assignment.model.Branch;
import com.assignment.model.Counter;
import com.assignment.model.Services;
import com.assignment.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Override
    public Branch registerBranch(Branch branch) throws Exception {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.save(branch);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return branch;
    }

    @Override
    public Counter registerCounter(Counter counter) throws Exception {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            long counterId =(long)session.save(counter);
            session.getTransaction().commit();
            counter.setCounterId(counterId);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return counter;
    }

    @Override
    public Services registerServices(Services services) throws Exception {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.save(services);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        return services;
    }
}
