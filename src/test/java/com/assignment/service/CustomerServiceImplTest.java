package com.assignment.service;

import com.assignment.enums.CustomerType;
import com.assignment.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void registerCustomer() throws Exception{
        Customer customer = new Customer();
        customer.setAccountNumber(1254367);
        customer.setCustomerType(CustomerType.PREMIUM);
        customer.setName("Rocky");
        customer.setAddress("Madhapur");
        customer = customerService.registerCustomer(customer);

        Customer newCustomer = customerService.findCustomer(customer.getAccountNumber());
        Assert.assertEquals(customer.getAccountNumber(),newCustomer.getAccountNumber());
        Assert.assertEquals(customer.getCustomerType(),newCustomer.getCustomerType());
        Assert.assertEquals(customer.getName(),newCustomer.getName());
        Assert.assertEquals(customer.getAddress(),newCustomer.getAddress());
    }

}