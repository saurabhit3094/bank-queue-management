package com.assignment.config;

import com.assignment.enums.CustomerType;
import com.assignment.model.Counter;
import com.assignment.model.Services;
import com.assignment.service.CounterService;
import com.assignment.service.MessageReceiver;
import com.assignment.service.MessageReceiverImpl;
import com.assignment.service.ServicesTypeService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQConfigBuild {

    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    CounterService counterService;

    @Autowired
    MessageReceiver receiver;
        public void registerQueues()throws Exception{
            List<Counter> counters = counterService.getAll();
        for (Counter counter : counters) {
            // Queue creation and binding of the rabbitmq consumer to the specific queue
            new RabbitMQConfig(counter.getServiceName() + "-" + counter.getCustomerType()   + "-key" + "-" + counter.getBranchId(),
                    counter.getServiceName() + "-" + counter.getCustomerType() + "-queue" + "-" + counter.getBranchId(), connectionFactory,
                    receiver);
        }
}
}
