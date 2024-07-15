package com.pedro.transactionproducer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Transaction implements Serializable {
    private AppEnum app;
    private String notificacao;
}
