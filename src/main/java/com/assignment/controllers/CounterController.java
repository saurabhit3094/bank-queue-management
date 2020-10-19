package com.assignment.controllers;

import com.assignment.dto.NextStepInputDTO;
import com.assignment.dto.TokenRequestDTO;
import com.assignment.enums.CustomerType;
import com.assignment.model.Counter;
import com.assignment.model.Token;
import com.assignment.model.TokenServiceMap;
import com.assignment.service.CounterService;
import com.assignment.service.CustomerService;
import com.assignment.service.TokenService;
import com.assignment.service.TokenServiceMapService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CounterController {

    @Autowired
    TokenServiceMapService tokenServiceMapService;

    @Autowired
    CounterService counterService;

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/counter/nextToken/{counterId}", method = RequestMethod.GET)
    public ResponseEntity nextToken(
            @PathVariable long counterId
    )throws Exception{

        Counter counter = counterService.getCounter(counterId);
        long tokenId = tokenServiceMapService.nextToken(counter.getServiceName(), CustomerType.valueOf(counter.getCustomerType()));

        //update active token on counter
        counterService.updateActiveToken(tokenId,counterId);

        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity("tokenId : "+tokenId, HttpStatus.OK);
    }

    @RequestMapping(value = "/counter/markComplete/{counterId}/{tokenId}", method = RequestMethod.PUT)
    public ResponseEntity markComplete(
            @PathVariable long counterId,
            @PathVariable long tokenId
    )throws Exception{

        Counter counter = counterService.getCounter(counterId);
        // mark completed in token service map table
        tokenServiceMapService.markCurrentServiceCompleted(tokenId,counter.getServiceName(),counter.getCustomerType(),counter.getBranchId());

        // Check if any service pending for this token in token service map
       List<TokenServiceMap> tokenServiceMap= tokenServiceMapService.isAnyPendingService(tokenId,counter.getBranchId());

        //token table mark status COMPLETED.
        if (tokenServiceMap == null || tokenServiceMap.isEmpty()) {
            tokenService.markStatusCompleted(tokenId);
        }

        //change active token status in counter table
        counterService.updateActiveToken(0,counterId);

        return  new ResponseEntity("Completed !", HttpStatus.OK);
    }

    @RequestMapping(value = "/counter/moveNext", method = RequestMethod.PUT)
    public ResponseEntity moveToNextStep(@RequestBody NextStepInputDTO nextStepInputDTO)throws Exception{
        counterService.moveToNextStep(nextStepInputDTO);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity("Queued to next service counter !", HttpStatus.OK);
    }


    @RequestMapping(value = "/counter/{branchId}", method = RequestMethod.GET)
    public ResponseEntity getCounters(@PathVariable long branchId)throws Exception{
            List<Counter> counter = counterService.getCounterForBranch(branchId);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ResponseEntity(gson.toJson(counter), HttpStatus.OK);
    }


}

