package com.example.barclays.inventory_management.model;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Ted Tapar
 * Simple POJO class to save inventory the generated report information
 */
public class Report {
	private BigDecimal profit;

	private BigDecimal totalValue;
	private Map<String,Item> inventoryItemMap = new HashMap<>();
	private Map<String,InventoryQuantity> inventoryQuantityMap = new HashMap<>();
			
	public Report(){}
	
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public Map<String, Item> getInventoryItemMap() {
		return inventoryItemMap;
	}

	public void setInventoryItemMap(Map<String, Item> inventoryItemMap) {
		this.inventoryItemMap = inventoryItemMap;
	}

	public Map<String, InventoryQuantity> getInventoryQuantityMap() {
		return inventoryQuantityMap;
	}

	public void setInventoryQuantityMap(Map<String, InventoryQuantity> inventoryQuantityMap) {
		this.inventoryQuantityMap = inventoryQuantityMap;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	
	
}
