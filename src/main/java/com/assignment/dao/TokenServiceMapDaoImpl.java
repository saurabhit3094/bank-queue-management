package com.assignment.dao;

import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.TokenServiceMap;
import com.assignment.service.TokenService;
import com.assignment.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TokenServiceMapDaoImpl implements TokenServiceMapDao {

    @Override
    public TokenServiceMap save(TokenServiceMap tokenServiceMap) throws Exception {
        try(Session session  = HibernateUtil.getSession()){
            session.save(tokenServiceMap);
        }catch(Exception e){
            throw  e;
        }
        return tokenServiceMap;
    }

    @Override
    public TokenServiceMap nextToken(String serviceName, CustomerType customerType)throws TokenException,Exception{
        TokenServiceMap tokenServiceMap=null;
        try(Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM TokenServiceMap where serviceName=:serviceName and customerType=:customerType and isCompleted='N' and isAnyServiceActive='N' order by id asc");
            query.setParameter("serviceName", serviceName);
            query.setParameter("customerType",customerType);
            List<TokenServiceMap> tokenServiceMapList = query.list();
            if (tokenServiceMapList ==null || tokenServiceMapList.isEmpty()){
                throw new TokenException("No Ticket in queue for this counter");
            }
            tokenServiceMap =  tokenServiceMapList.get(0);
        }catch (Exception e) {
            throw e;
        }
     return tokenServiceMap;
    }

    @Override
    public void updateIsServiceActive(char isServiceActive,long tokenId)throws Exception{
        try (Session session = HibernateUtil.getSession()){
            session.getTransaction().begin();
           Query query= session.createQuery("UPDATE TokenServiceMap SET isAnyServiceActive=:isAnyServiceActive where tokenId=:tokenId");
            query.setParameter("tokenId", tokenId);
            query.setCharacter("isAnyServiceActive",isServiceActive);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void markCurrentServiceCompleted(long tokenId, String serviceName, long branchId) {
        Query query = null;
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            query = session.createQuery("update TokenServiceMap set isCompleted='Y' where tokenId = :tokenId and branchId = :branchId and serviceName=:serviceName and isCompleted='N' and isAnyServiceActive='Y'");
            query.setParameter("tokenId",tokenId);
            query.setParameter("branchId",branchId);
            query.setParameter("serviceName",serviceName);
            query.executeUpdate();
            query = session.createQuery("update TokenServiceMap set isAnyServiceActive='N' where tokenId = :tokenId and branchId = :branchId");
            query.setParameter("tokenId",tokenId);
            query.setParameter("branchId",branchId);
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<TokenServiceMap> isAnyPendingService(long tokenId, long branchId) throws Exception {
        List<TokenServiceMap> tokenServiceMap=null;
        try(Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM TokenServiceMap where tokenId=:tokenId and branchId=:branchId and isCompleted='N'");
            query.setParameter("tokenId", tokenId);
            query.setParameter("branchId",branchId);
            tokenServiceMap = query.list();

        }catch (Exception e) {
            throw e;
        }
        return tokenServiceMap;
    }

    @Override
    public List<TokenServiceMap> getTokenServiceMapForTokenID(long tokenId) throws Exception {

        List<TokenServiceMap> tokenServiceMap=null;
        try(Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM TokenServiceMap where tokenId=:tokenId");
            query.setParameter("tokenId", tokenId);
            tokenServiceMap = query.list();

        }catch (Exception e) {
            throw e;
        }
        return tokenServiceMap;
    }

    @Override
    public List<TokenServiceMap> getAllPendingTokenList(long branchId) throws TokenException, Exception {
        List<TokenServiceMap> tokenServiceMap=null;
        try(Session session = HibernateUtil.getSession()) {
            Query query = session.createQuery("FROM TokenServiceMap where branchId=:branchId");
            query.setParameter("branchId", branchId);
            tokenServiceMap = query.list();
            if (tokenServiceMap== null || tokenServiceMap.isEmpty()){
                new TokenException("No Pending token available");
            }
        }catch (Exception e) {
            throw e;
        }
        return tokenServiceMap;
    }
}
