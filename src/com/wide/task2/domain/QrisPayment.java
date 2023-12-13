package com.wide.task2.domain;

public class QrisPayment extends Payment {
    public QrisPayment() {
    }

  

    @Override
    public void processPayment(Sale sale) {
    	 System.out.println("Payment successful!");
         System.out.println("Payment Method: QRIS");
    }
}
