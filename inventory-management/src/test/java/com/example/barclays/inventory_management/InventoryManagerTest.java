package com.example.barclays.inventory_management;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.example.barclays.inventory_management.businesslogic.InventoryManager;
import com.example.barclays.inventory_management.exceptions.ItemAlreadyExistException;
import com.example.barclays.inventory_management.exceptions.ItemNotFoundException;
import com.example.barclays.inventory_management.model.InventoryQuantity;
import com.example.barclays.inventory_management.model.Item;
import com.example.barclays.inventory_management.model.Report;


public class InventoryManagerTest{
	
	InventoryManager inventoryManager =new InventoryManager();
	Report testReport;
	@Before
	public  void addTestData() throws FileNotFoundException{

		inventoryManager.createItem("Book01", "10.50", "13.79");
		inventoryManager.createItem("Food01", "1.47", "3.98");
		inventoryManager.createItem("Med01", "30.63", "34.29");
		inventoryManager.createItem("Tab01", "57.00", "84.98");
		inventoryManager.updateBuyItem("Tab01", "100");
		inventoryManager.updateBuyItem("Food01", "500");
		inventoryManager.updateBuyItem("Book01", "100");
		inventoryManager.updateBuyItem("Med01", "100");
			
	}
	@Test
	public void createItem_addNewItem_test() {		
		inventoryManager.createItem("Item01", "1.50", "3.00");
		Report report = inventoryManager.reportItem();
		Map<String,Item> map = report.getInventoryItemMap();
		String key="Item01";
		Item item = map.get(key.trim().toLowerCase());
		assertEquals(item.getItemName(), "Item01");
		assertEquals(item.getCostPrice(), new BigDecimal("1.50"));
		assertEquals(item.getSellPrice(), new BigDecimal("3.00"));
	}
	
	@Test(expected=ItemAlreadyExistException.class)
	public void createItem_alreadyExist_test(){
		inventoryManager.createItem("Item01", "1.50","3.00" );
		inventoryManager.createItem("Item01", "1.50","3.00" );
	} 
	
	@Test
	public void deleteItem_test() {		
		Report report = inventoryManager.reportItem();
		inventoryManager.deleteItem("Tab01");
		Map<String,Item> map = report.getInventoryItemMap();
		String key="Tab01";
		assertFalse(map.containsKey(key));
	}
   
	@Test(expected=ItemNotFoundException.class)
	public void deleteItem_NotFound_test() {		
		inventoryManager.deleteItem("Dummy01");	
	}
	
	@Test
	public void updateBuyItem_addNewQuantity_test() {		
		Report report = inventoryManager.reportItem();
		inventoryManager.updateBuyItem("Book01", "300");
		Map<String,InventoryQuantity> map = report.getInventoryQuantityMap();
		String key="Book01";
		InventoryQuantity inventoryQuantity =map.get(key.trim().toLowerCase());
		assertEquals(inventoryQuantity.getQuantity(), new Integer("400"));
	}
	
	@Test(expected=ItemNotFoundException.class)
	public void updateBuyItem_NotFound_test() {		
		inventoryManager.updateBuyItem("Dummy01", "100");	
	}
	
	@Test
	public void updateSellItem_sellQuantity_test() {		
		Report report = inventoryManager.reportItem();
		inventoryManager.updateSellItem("Book01", "2");
		Map<String,InventoryQuantity> map = report.getInventoryQuantityMap();
		String key="Book01";
		InventoryQuantity inventoryQuantity =map.get(key.trim().toLowerCase());
		assertEquals(inventoryQuantity.getQuantity(), new Integer("98"));
	}
	
	@Test(expected=ItemNotFoundException.class)
	public void sellItem_NotFound_test() {		
		inventoryManager.updateSellItem("Dummy01", "100");	
	}
	
	@Test
	public void updateSellPriceItem_changeSellPrice_test() {		
		Report report = inventoryManager.reportItem();
		inventoryManager.updateSellPriceItem("Book01", "15.00");
		Map<String,Item> map = report.getInventoryItemMap();
		String key="Book01";
		Item item =map.get(key.trim().toLowerCase());
		assertEquals(item.getSellPrice(), new BigDecimal("15.00"));
	}
	
	@Test(expected=ItemNotFoundException.class)
	public void updateSellPriceItem_NotFound_test() {		
		inventoryManager.updateSellPriceItem("Dummy01", "100");	
	}
	
	
}
