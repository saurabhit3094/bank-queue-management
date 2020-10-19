package com.assignment.controllers;

import com.assignment.dto.TokenRequestDTO;
import com.assignment.enums.CustomerType;
import com.assignment.model.Customer;
import com.assignment.model.Token;
import com.assignment.model.TokenServiceMap;
import com.assignment.service.CustomerService;
import com.assignment.service.TokenService;
import com.assignment.service.TokenServiceMapService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TokenController {


    @Autowired
    TokenService tokenService;

    @Autowired
    TokenServiceMapService tokenServiceMapService;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity generateToken(@RequestBody TokenRequestDTO tokenRequestDTO)throws Exception{
        Token token = tokenService.createTokenAndAssignToQueue(tokenRequestDTO);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(token), HttpStatus.OK);
    }

    @RequestMapping(value = "/token/pending/{branchId}",method = RequestMethod.GET)
    public ResponseEntity getAllPendingToken(@PathVariable long branchId)throws Exception{
        List<TokenServiceMap> tokenServiceMap = tokenServiceMapService.getAllPendingTokenList(branchId);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(tokenServiceMap), HttpStatus.OK);
    }
}
