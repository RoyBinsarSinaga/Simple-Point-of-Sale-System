package com.wide.task2.domain;

public class SaleItem {
    private int quantity;
    private Item item;
    public static final double TAX_RATE = 0.1;

    public SaleItem(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }
    
    public SaleItem() {
    } 

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }
    
   
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double calculateTotalPrice() {
        return quantity * item.getPrice();
    }
    
    public double calculateTax() {
        if (item.isTaxable()) {
            return item.getPrice() * TAX_RATE;
        } else {
            return 0; 
        }
    }
}