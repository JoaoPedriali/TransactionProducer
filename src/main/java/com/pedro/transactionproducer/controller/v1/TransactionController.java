package com.pedro.transactionproducer.controller.v1;

import com.pedro.transactionproducer.model.Transaction;
import com.pedro.transactionproducer.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final MessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<String> publishTransaction(@RequestBody Transaction transaction) {
        try {
            messageProducer.send(transaction);
            return ResponseEntity.ok("Transaction published");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
