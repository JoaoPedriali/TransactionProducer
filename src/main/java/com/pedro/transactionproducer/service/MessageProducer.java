package com.pedro.transactionproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedro.transactionproducer.model.Transaction;
import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageProducer {

    private final ObjectMapper mapper;
    private final JmsTemplate jmsTemplate;

    @Value("${spring.artemis.embedded.queues}")
    private String queueName;

    public void send(Transaction transaction) {
        try {
            log.info("transaction content :: {}", transaction.toString());

            jmsTemplate.convertAndSend(queueName, transaction, message -> {
                message.setStringProperty("_type","com.pedro.financing.controller.v1.request.TransactionRequest");
                log.info("Message :: {}", message);
                return message;
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
