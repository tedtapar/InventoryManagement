package com.example.barclays.inventory_management.businesslogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.barclays.inventory_management.exceptions.ItemAlreadyExistException;
import com.example.barclays.inventory_management.exceptions.ItemNotFoundException;
import com.example.barclays.inventory_management.exceptions.NotEnoughQuantityException;
import com.example.barclays.inventory_management.model.InventoryQuantity;
import com.example.barclays.inventory_management.model.Item;
import com.example.barclays.inventory_management.model.Report;
import com.example.barclays.inventory_management.utilities.ReportHelper;

/**
 * 
 * @author Ted Tapar
 * Inventory Manager class consist of all the logic. It has two diffrent maps one
 * for all the items and for all storing quantities of those items, profit continuously
 * accumlates the profit as any transaction occurs, reportHelper helps to generate the 
 * profit information.
 */
public class InventoryManager {
	private Map<String, Item> inventoryItemMap= new HashMap<String, Item>();
	private Map<String,InventoryQuantity> inventoryQuantityMap = new HashMap<>(); 
	private BigDecimal profit=new BigDecimal(0);
	private ReportHelper reportHelper= new ReportHelper();
	
	/**
	 * @param itemName
	 * @param costPrice
	 * @param sellPrice
	 * createItem creates the item and puts it into a map, it throws ItemAlreadyExistException
	 * if item already exist in the map
	 */
	public void createItem(String itemName,String costPrice, String sellPrice ) {
		Item item= new Item(itemName,new BigDecimal(costPrice),new BigDecimal(sellPrice),0);
		String key=itemName.trim().toLowerCase();
		if(inventoryItemMap.containsKey(key)){
				throw new ItemAlreadyExistException(itemName);
			}else{
			inventoryItemMap.put(key, item);
		}
	}
	/**
	 * @param itemName
	 * deleteItem deletes the item from the items map and quantity map, it throws
	 * ItemNotFoundException if item does not exist in the maps
	 */
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
	/**
	 * @param itemName
	 * @param quantity
	 * updateBuyItem updates the available quantity of items by adding in to the existing .
	 * quantity. It throws ItemNotFoundException if item does not exist in the maps
	 */
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
	
	/**
	 * @param itemName
	 * @param quantity
	 * updateSellItem updates the quantity and calculates the profit. It throws
	 * NotEnoughQunatityException if the requested quantity is greater then available 
	 * quantity also it throws ItemNotFoundException if item does not exist in the maps
	 */
	public void updateSellItem(String itemName, String quantity){
		String key=itemName.trim().toLowerCase();
		if(!inventoryItemMap.containsKey(key) || !inventoryQuantityMap.containsKey(key)){
			throw new ItemNotFoundException(itemName);
		}else{
			Item item = inventoryItemMap.get(key);
			
			InventoryQuantity inventoryQunatity=inventoryQuantityMap.get(key);
			if(inventoryQunatity.getQuantity()<Integer.parseInt(quantity)){
				throw new NotEnoughQuantityException(itemName);
			}
			else{
			inventoryQunatity.setQuantity(inventoryQunatity.getQuantity()-Integer.parseInt(quantity));
			inventoryQuantityMap.put(key, inventoryQunatity);
			profit= profit.add((item.getSellPrice().subtract(item.getCostPrice()).multiply(new BigDecimal(quantity))));
			}
			inventoryItemMap.put(key,item);
		}
	}
	
	/**
	 * @param itemName
	 * @param newSellPrice
	 * updateSellPriceItem updates the selling price of item, it throws ItemNotFoundException
	 *  if item does not exist in the maps
	 */
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
	
	/**
	 * @return Report
	 * reportItem generates the report and returns report object
	 */
	public Report reportItem(){	
		Report report =new Report();
		report.setProfit(profit.subtract(reportHelper.getSum()));
		report.setInventoryItemMap(inventoryItemMap);
		report.setInventoryQuantityMap(inventoryQuantityMap);
		reportHelper.getReports().add(report);		
		return report;
	}

}
