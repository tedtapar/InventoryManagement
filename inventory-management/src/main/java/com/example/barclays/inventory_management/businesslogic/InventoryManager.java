package com.example.barclays.inventory_management.businesslogic;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.barclays.inventory_management.exceptions.ItemAlreadyExistException;
import com.example.barclays.inventory_management.exceptions.ItemNotFoundException;
import com.example.barclays.inventory_management.model.InventoryQuantity;
import com.example.barclays.inventory_management.model.Item;
import com.example.barclays.inventory_management.model.Report;
import com.example.barclays.inventory_management.utilities.ReportHelper;


public class InventoryManager {
	private Map<String, Item> inventoryItemMap= new HashMap<String, Item>();
	private Map<String,InventoryQuantity> inventoryQuantityMap = new HashMap<>(); 
	private BigDecimal profit=new BigDecimal(0);
	private ReportHelper reportHelper= new ReportHelper();
	
	public void createItem(String itemName,String costPrice, String sellPrice ) {
		Item item= new Item(itemName,new BigDecimal(costPrice),new BigDecimal(sellPrice),0);
		String key=itemName.trim().toLowerCase();
		if(inventoryItemMap.containsKey(key)){
				throw new ItemAlreadyExistException(itemName);
			}else{
			inventoryItemMap.put(key, item);
		}
	}
	
	public void deleteItem(String itemName){
		String key=itemName.trim().toLowerCase();
		if(!inventoryItemMap.containsKey(key) || !inventoryQuantityMap.containsKey(key)){
			throw new ItemNotFoundException(itemName);
		}else{
			Item item= inventoryItemMap.get(key);
			InventoryQuantity inventoryQuantity =inventoryQuantityMap.get(key);
			profit= profit.subtract(item.getCostPrice().multiply(new BigDecimal(inventoryQuantity.getQuantity())));
			inventoryQuantityMap.remove(key);
			inventoryItemMap.remove(key);
		}
	}
	
	public void updateBuyItem(String itemName, String quantity){
		String key=itemName.trim().toLowerCase();
		if(!inventoryItemMap.containsKey(key)){
			throw new ItemNotFoundException(itemName);
		}else{
			Item item = inventoryItemMap.get(key);
			if(inventoryQuantityMap.containsKey(key)){
				InventoryQuantity inventoryQunatity=inventoryQuantityMap.get(key);
				inventoryQunatity.setQuantity(inventoryQunatity.getQuantity()+Integer.parseInt(quantity));
				inventoryQuantityMap.put(key, inventoryQunatity);
			}else{
				InventoryQuantity inventoryQunatity=new InventoryQuantity(itemName,Integer.parseInt(quantity));
				inventoryQuantityMap.put(key,inventoryQunatity);
				
			}
			inventoryItemMap.put(key,item);
		}
	}
	
	public void updateSellItem(String itemName, String quantity){
		String key=itemName.trim().toLowerCase();
		if(!inventoryItemMap.containsKey(key) || !inventoryQuantityMap.containsKey(key)){
			throw new ItemNotFoundException(itemName);
		}else{
			Item item = inventoryItemMap.get(key);
			profit= profit.add((item.getSellPrice().subtract(item.getCostPrice()).multiply(new BigDecimal(quantity))));
			InventoryQuantity inventoryQunatity=inventoryQuantityMap.get(key);
			inventoryQunatity.setQuantity(inventoryQunatity.getQuantity()-Integer.parseInt(quantity));
			inventoryQuantityMap.put(key, inventoryQunatity);
			inventoryItemMap.put(key,item);
		}
	}
	
	
	public void updateSellPriceItem(String itemName, String newSellPrice){
		String key=itemName.trim().toLowerCase();
		if(!inventoryItemMap.containsKey(key)){
			throw new ItemNotFoundException(itemName);
		}else{
			Item item = inventoryItemMap.get(key);
			item.setSellPrice(new BigDecimal(newSellPrice));
			inventoryItemMap.put(key,item);
		}
	}
	
	
	public Report reportItem(){	
		Report report =new Report();
		report.setProfit(profit.subtract(reportHelper.getSum()));
		report.setInventoryItemMap(inventoryItemMap);
		report.setInventoryQuantityMap(inventoryQuantityMap);
		reportHelper.getReports().add(report);		
		return report;
	}

}
