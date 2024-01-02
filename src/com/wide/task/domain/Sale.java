package com.wide.task.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wide.task.repository.SaleRepository;

public class Sale {
    private int saleNumber;
    private String cashier;
    private Date date;
    private List<SaleItem> saleitemsList = new ArrayList<SaleItem>();

    public Sale(int saleNumber, String cashier, Date date) {
        this.saleNumber = saleNumber;
        this.cashier = cashier;
        this.date = date;
    }
    
    public Sale(Date date) {
    	this.date = date;
    }
    
    public Sale() {
    }
    
    
    public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public void addSaleItem(SaleItem saleItem) {
	        saleitemsList.add(saleItem); 
	}

    public int getSaleNumber() {
        return saleNumber;
    }

    public String getCashier() {
        return cashier;
    }

    public Date getDate() {
        return date;
    }

    public List<SaleItem> getSaleItem() {
    	return new ArrayList<>(saleitemsList);
	}
    
    public double calculateTotalTax() {
        double totalTax = 0;
        for (SaleItem saleItem : saleitemsList) {
            if (saleItem != null) {
                totalTax += saleItem.calculateTax();
            }
        }
        return totalTax; 
    } 
	
    public double calculateTotalPrices() {
        double totalPrice = 0;
        for (SaleItem saleItem : saleitemsList) {
            if (saleItem != null) {
                totalPrice += saleItem.calculateTotalPrice();
            }
        }
        return totalPrice; 
    }
    
    public double calculateTotalPricenTax() {
        double totalPricenTax = 0;
        for (SaleItem saleItem : saleitemsList) {
            if (saleItem != null) {
                totalPricenTax += saleItem.calculateTotalPrice() + saleItem.calculateTax(); 
            }
        }
        return totalPricenTax; 
    }
    
	public void finish(SaleRepository saleRepository) {
		 Sale savedSale = saleRepository.findByNumber(this.saleNumber);
	     System.out.println("\nSALE COMPLETE FOR CASHIER: " + savedSale.getCashier());
	}

	
    
}