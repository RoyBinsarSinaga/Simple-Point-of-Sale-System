package com.sale.app.domain;

public class SaleItem {
    private String saleNumber;
    private Item item;
    private int quantity;
    private int totalPrice;

    public SaleItem(String saleNumber, Item item, int quantity) {
        this.saleNumber = saleNumber;
        this.item = item;
        this.quantity = quantity;
        calculateTotalPrice();
    }

    public String getSaleNumber() {
        return saleNumber;
    }
    

    public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = item.getPrice() * quantity;
    }

	
}
