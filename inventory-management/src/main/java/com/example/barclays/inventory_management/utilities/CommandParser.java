package com.example.barclays.inventory_management.utilities;

import com.example.barclays.inventory_management.businesslogic.InventoryManager;
import com.example.barclays.inventory_management.model.Report;

public class CommandParser {
	private InventoryManager inventoryManager =new InventoryManager();
	public void command(String command){
		
		String[] commandSplits= command.split("\\s+");
		switch(commandSplits[0].trim().toLowerCase()){
			case "create":
				inventoryManager.createItem(commandSplits[1],commandSplits[2], commandSplits[3]);
				break;
			case "delete":
				inventoryManager.deleteItem(commandSplits[1]);
				break;	
			case "updatebuy":
				inventoryManager.updateBuyItem(commandSplits[1],commandSplits[2]);
				break;	
			case "updatesell":
				inventoryManager.updateSellItem(commandSplits[1],commandSplits[2]);
				break;
			case "updatesellprice":
				inventoryManager.updateSellPriceItem(commandSplits[1], commandSplits[2]);
				break;
			case "report":
				PrintUtility printUtility= new PrintUtility();
				Report report=inventoryManager.reportItem();
				printUtility.printReport(report);
				break;		
		}
	}

}
