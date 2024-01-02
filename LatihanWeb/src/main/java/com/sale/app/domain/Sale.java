package com.sale.app.domain;

import java.util.List;

public class Sale {
    private String saleNumber;
    private List<SaleItem> saleItems;
    private int totalSalePrice;

    public Sale(String saleNumber, List<SaleItem> saleItems) {
        this.saleNumber = saleNumber;
        this.saleItems = saleItems;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public int getTotalSalePrice() {
        return totalSalePrice;
    }

//    private void calculateTotalSalePrice() {
//        this.totalSalePrice = saleItems.stream().mapToInt(SaleItem::getTotalPrice).sum();
//    }

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	
}

