package com.assignment.service;

import com.assignment.dto.QueueDTO;
import com.assignment.dto.TokenRequestDTO;
import com.assignment.enums.CustomerType;
import com.assignment.exception.TokenException;
import com.assignment.model.Customer;
import com.assignment.model.Token;
import com.assignment.utils.HibernateUtil;
import com.assignment.dao.TokenDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    CustomerService customerService;

    @Autowired
    TokenDao tokenDao;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public Token    createTokenAndAssignToQueue(TokenRequestDTO tokenRequestDTO) throws TokenException,Exception {

        validateTokenInput(tokenRequestDTO);

        //get customer details
        Customer customer = customerService.findCustomer(tokenRequestDTO.getAccountNumber());
        if (customer == null){
            throw new TokenException("Csutomer is not Registered");
        }

        Token token = saveToken(tokenRequestDTO, customer);

        //queue token for corresponding service queue
        for (String service : tokenRequestDTO.getServicesList()){
            queueToken(token, service, customer.getCustomerType());
        }

        return token;
    }

    private void validateTokenInput(TokenRequestDTO tokenRequestDTO) throws Exception{
        if (tokenRequestDTO.getAccountNumber() == null){
            throw new TokenException("Account number can not be null");
        }else if (tokenRequestDTO.getBranchId() ==null){
            throw new TokenException("Branch Id can not be null");
        }else if (tokenRequestDTO.getServicesList() == null || tokenRequestDTO.getServicesList().isEmpty()){
            throw new TokenException("Atleast one service required");
        }
    }

    private Token saveToken(TokenRequestDTO tokenRequestDTO, Customer customer) throws Exception {
        //create new token
        Token token = new Token();
        token.setCustomerAccNum(customer.getAccountNumber());
        token.setCreateDate(new Date());
        token.setBranchId(tokenRequestDTO.getBranchId());
        token.setStatus("IN-PROGRESS");
        tokenDao.saveToken(token);
        return token;
    }

    public void queueToken(Token token, String service,CustomerType customerType)throws Exception {
            QueueDTO queueDTO = new QueueDTO();
            queueDTO.setToken(token);
            queueDTO.setCustomerType(customerType);
            queueDTO.setServiceName(service);
            queueDTO.setBranchId(token.getBranchId());
        MessageProperties properties = new MessageProperties();
        properties.setHeader("commandType", "CREATE");
        properties.setHeader("content-type", "text/plain");

        Message message = new Jackson2JsonMessageConverter().toMessage(queueDTO, properties);
        rabbitTemplate.setExchange("token-exchange");
        rabbitTemplate.setRoutingKey(service + "-" + customerType   + "-key" + "-" + token.getBranchId());
        rabbitTemplate.send(message);

//            rabbitTemplate.convertAndSend("token-exchange",service + "-" + customerType   + "-key" + "-" + token.getBranchId(), "Hello World");
    }

    @Override
    public Token getToken(long tokenId) throws Exception {
        Token token=null;
        try(Session session = HibernateUtil.getSession()){
            token = session.get(Token.class,tokenId);
        }catch (Exception e){
            throw e;
        }
        return token;
    }

    @Override
    public void markStatusCompleted(long tokenId) throws Exception {
        try(Session session = HibernateUtil.getSession()){
            Query query = session.createQuery("update Token set status='COMPLETED' where tokenId=:tokenId");
            query.setParameter("tokenId",tokenId);
            query.executeUpdate();
        }catch (Exception e){
            throw e;
        }
    }
}
