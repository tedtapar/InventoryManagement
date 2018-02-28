package com.example.barclays.inventory_management.model;

/**
 * 
 * @author Ted Tapar
 * Simple POJO class to save inventory item information
 */
import java.math.BigDecimal;

public class Item {
	private String itemName;
	private BigDecimal costPrice;
	private BigDecimal sellPrice;
	
	public Item(){
		
	} 
	
	public Item(String itemName, BigDecimal costPrice, BigDecimal sellPrice, Integer availableQuantity) {
		super();
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	
	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", costPrice=" + costPrice + ", sellPrice=" + sellPrice+ "]";
	}

	
	
}
