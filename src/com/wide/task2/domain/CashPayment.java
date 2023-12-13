package com.wide.task2.domain;

public class CashPayment extends Payment {
    private double cashInHand;

    public CashPayment( double cashInHand) {
        this.cashInHand = cashInHand;
    }

   
    @Override
    public void processPayment(Sale sale) {
            double change = cashInHand - sale.calculateTotalPricenTax();
            System.out.println("Cash in Hand: " + cashInHand);
            System.out.println("Payment successful!");
            System.out.println("Payment Method: Cash");
            System.out.println("Change: " + change);
    }
}
