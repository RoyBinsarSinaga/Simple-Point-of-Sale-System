package com.wide.task.domain;

public abstract class Payment {

    public Payment( ) {
    }

    public abstract void processPayment(Sale sale);
}