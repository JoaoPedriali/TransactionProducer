package com.pedro.transactionproducer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class Transaction implements Serializable {
    private AppEnum app;
    private String notificacao;
}
