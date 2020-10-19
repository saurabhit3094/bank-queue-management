package com.assignment.dao;

import com.assignment.model.Counter;
import com.assignment.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CounterDaoImpl implements CounterDao {
    @Override
    public List<Counter> getAll() throws Exception {
        List<Counter> counterList=null;
        try(Session session = HibernateUtil.getSession()){
            Query query = session.createQuery("from Counter");
            counterList = query.list();
        }catch (Exception e){
            throw e;
        }
        return counterList;
    }

    @Override
    public Counter getCounter(long counterId) throws Exception {
        Counter  counter=null;
        try(Session session = HibernateUtil.getSession()){
            counter = session.get(Counter.class,counterId);
        }catch (Exception e){
            throw e;
        }
        return counter;
    }

    @Override
    public void updateActiveToken(long tokenId, long counterId) throws Exception {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            Query query =session.createQuery("update Counter set activeTokenId = :tokenId where counterId=:counterId");
            query.setParameter("tokenId",tokenId);
            query.setParameter("counterId",counterId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Counter> getCounterForBranch(long branchId) throws Exception {
        List<Counter> counterList=null;
        try(Session session = HibernateUtil.getSession()){
            Query query = session.createQuery("from Counter where branchId=:branchId");
            query.setParameter("branchId",branchId);
            counterList = query.list();
        }catch (Exception e){
            throw e;
        }
        return counterList;
    }
}
