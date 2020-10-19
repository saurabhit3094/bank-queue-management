package com.assignment.dao;

import com.assignment.model.Customer;

public interface CustomerDao {
    Customer registerCustomer(Customer customer);

    Customer findCustomer(long customerId);

    long deleteCustomer(long customerId);
}
