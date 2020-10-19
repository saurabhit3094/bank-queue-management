package com.assignment.controllers;

import com.assignment.config.RabbitMQConfigBuild;
import com.assignment.model.Branch;
import com.assignment.model.Counter;
import com.assignment.model.Customer;
import com.assignment.model.Services;
import com.assignment.service.AdminService;
import com.assignment.service.CounterService;
import com.assignment.service.CustomerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RabbitMQConfigBuild rabbitMQConfigBuild;

    @Autowired
    AdminService adminService;

    /*
     * Create new customer
     * input parameter : customer details
     * output parameter : customer json
     * Request type : POST
     *
     * */
    @RequestMapping(value="/customer",method = RequestMethod.POST)
    public ResponseEntity registerCustomer (
            @RequestBody Customer customer
    )throws Exception{
        Customer customerRet = customerService.registerCustomer(customer);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(customerRet), HttpStatus.CREATED);
    }

    /*
     * get customer details
     * input parameter : Customer Id
     * output parameter : Customer json
     * Request type : GET
     *
     * */
    @RequestMapping(value="/customer/{customerId}",method = RequestMethod.GET)
    public ResponseEntity getCustomer(
            @PathVariable long customerId
    )throws Exception{
        Customer customer = customerService.findCustomer(customerId);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (customer == null){
            return new ResponseEntity<>("Customer does not exists", HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson(customer), HttpStatus.OK);
    }

    @RequestMapping(value="/service/register",method = RequestMethod.GET)
    public ResponseEntity registerService()throws Exception{
        rabbitMQConfigBuild.registerQueues();
        return new ResponseEntity<>("Service Queues registered successfully ! ", HttpStatus.OK);
    }


    @RequestMapping(value="/branch",method = RequestMethod.POST)
    public ResponseEntity registerBranch (
            @RequestBody Branch branch
    )throws Exception{
         branch = adminService.registerBranch(branch);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(branch), HttpStatus.CREATED);
    }


    @RequestMapping(value="/counter",method = RequestMethod.POST)
    public ResponseEntity registerCounter (
            @RequestBody Counter counter
    )throws Exception{
        counter = adminService.registerCounter(counter);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(counter), HttpStatus.CREATED);
    }

    @RequestMapping(value="/service",method = RequestMethod.POST)
    public ResponseEntity registerService (
            @RequestBody Services services
    )throws Exception{
        services = adminService.registerServices(services);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(services), HttpStatus.CREATED);
    }


}

