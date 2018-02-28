package com.example.barclays.inventory_management.utilities;

import java.math.BigDecimal;

import com.example.barclays.inventory_management.model.Report;


public class PrintUtility {
	public void printReport(Report report){
		BigDecimal total=new BigDecimal(0);
		System.out.println("			INVENTORY REPORT");
		System.out.println("Item Name 	Bought At 	Sold AT 	AvailableQty	 Value");
		System.out.println("--------- 	--------- 	------- 	------------	 -----");
		for(String key:report.getInventoryItemMap().keySet()){
			BigDecimal value= report.getInventoryItemMap().get(key).getCostPrice().multiply(new BigDecimal(report.getInventoryQuantityMap().get(key).getQuantity()));
			total=total.add(value);
			System.out.println(report.getInventoryItemMap().get(key).getItemName() + "		" +
							 report.getInventoryItemMap().get(key).getCostPrice()+ "		" +
							 report.getInventoryItemMap().get(key).getSellPrice()+ "		" +
							 report.getInventoryQuantityMap().get(key).getQuantity()+ "		" + 
							 value);
		}
		report.setTotalValue(total);
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Total value                                  			 "+ report.getTotalValue());
		System.out.println("Profit since previous report                			  "+ report.getProfit());
		System.out.println();
	}
}
