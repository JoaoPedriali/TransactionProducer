package com.pedro.transactionproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.transactionproducer.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageProducer {
    Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final ObjectMapper mapper;
    private final JmsTemplate jmsTemplate;

    @Value("${spring.artemis.embedded.queues}")
    private String queueName;

    public void send(Transaction transaction) {
        try {
            logger.info("Sending message :: {}", transaction.toString());
            jmsTemplate.convertAndSend(queueName, transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
