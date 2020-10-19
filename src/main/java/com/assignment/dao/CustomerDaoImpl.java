package com.assignment.dao;

import com.assignment.model.Customer;
import com.assignment.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {


    @Override
    public Customer registerCustomer(Customer customer) {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
              session.save(customer);
              session.getTransaction().commit();
        }
        return customer;
    }

    @Override
    public Customer findCustomer(long customerId) {
        Customer customer=null;
        try(Session session = HibernateUtil.getSession()) {
            customer = session.get(Customer.class,customerId);
        }
        return customer;
    }

    @Override
    public long deleteCustomer(long customerId) {
        try(Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            Customer customer = session.get(Customer.class,customerId);
            session.delete(customer);
            session.getTransaction().commit();
        }
        return customerId;
    }
}
