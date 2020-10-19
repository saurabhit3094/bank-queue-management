package com.assignment.service;

import com.assignment.model.Customer;
import com.assignment.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerDao.registerCustomer(customer);
    }

    @Override
    public Customer findCustomer(long customerId) {
        return customerDao.findCustomer(customerId);
    }
}
