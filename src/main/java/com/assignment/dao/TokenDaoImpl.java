package com.assignment.dao;

import com.assignment.model.Token;
import com.assignment.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDaoImpl implements TokenDao {
    @Override
    public Token saveToken(Token token) throws Exception {

        try(Session session  = HibernateUtil.getSession()){
            session.getTransaction().begin();
            session.save(token);
            session.getTransaction().commit();
        }
        return token;
    }
}
