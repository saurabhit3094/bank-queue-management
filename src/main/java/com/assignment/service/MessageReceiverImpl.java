package com.assignment.service;

import com.assignment.dto.QueueDTO;
import com.assignment.model.TokenServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MessageReceiverImpl implements MessageReceiver {

    @Autowired
    TokenServiceMapService tokenServiceMapService;

    @Override
    public void receiveMessage(QueueDTO queueDTO) throws Exception {

        System.out.println("Token received for service : " + queueDTO.getServiceName());

        // Make entry in service-token-map
        TokenServiceMap tokenServiceMap = new TokenServiceMap();
        tokenServiceMap.setTokenId(queueDTO.getToken().getTokenId());
        tokenServiceMap.setServiceName(queueDTO.getServiceName());
        tokenServiceMap.setCustomerType(queueDTO.getCustomerType());
        tokenServiceMap.setBranchId(queueDTO.getBranchId());
        tokenServiceMap.setIsCompleted('N');
        tokenServiceMap.setIsAnyServiceActive('N');
        tokenServiceMapService.save(tokenServiceMap);

    }
}
