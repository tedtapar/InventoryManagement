package com.example.barclays.inventory_management.model;

/**
 * 
 * @author Ted Tapar
 * Simple POJO class to save inventory item and it's quantity
 */
public class InventoryQuantity {
	private String itemName;
	private Integer quantity;
	

	public InventoryQuantity(String itemName, Integer quantity) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InventoryQunatity [itemName=" + itemName + ", quantity=" + quantity + "]";
	}
	
}
