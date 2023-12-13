package com.wide.task2.domain;

public abstract class Payment {

    public Payment( ) {
    }

    public abstract void processPayment(Sale sale);
}