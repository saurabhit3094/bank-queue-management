package com.assignment.service;

import com.assignment.model.Customer;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer findCustomer(long customerId);
}
