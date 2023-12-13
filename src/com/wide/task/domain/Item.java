package com.wide.task.domain;

public class Item {
    private String itemCode;
    private String description;
    private String type;
    private double price;
    private boolean isTaxable;

    public Item(String itemCode, String description, String type, double price, boolean isTaxable) {
        this.itemCode = itemCode;
        this.description = description;
        this.type = type;
        this.price = price;
        this.isTaxable = isTaxable;
    }
    
    public Item() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
    

    public double getPrice() {
        return price;
    }
    
    
    public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setTaxable(boolean isTaxable) {
		this.isTaxable = isTaxable;
	}

	public boolean isTaxable( ) {
    	return isTaxable;
    }
    
    
}

