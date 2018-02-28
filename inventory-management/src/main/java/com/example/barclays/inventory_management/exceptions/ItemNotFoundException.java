package com.example.barclays.inventory_management.exceptions;
/**
 * @author Ted Tapar
 * Exception class to throw exception when item is not found
 */

public class ItemNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(String itemName){
		super(itemName + " item not found!");
	}
}
