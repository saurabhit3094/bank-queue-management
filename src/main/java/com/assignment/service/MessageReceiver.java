package com.assignment.service;

import com.assignment.dto.QueueDTO;

public interface MessageReceiver {

    public void receiveMessage(QueueDTO queueDTO) throws Exception;
}
