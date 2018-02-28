package com.example.barclays.inventory_management.exceptions;
/**
 * @author Ted Tapar
 * Exception class to throw exception when item already exists
 */
public class ItemAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistException(String itemName){
		super(itemName + " Already exist in the inventory");
	}
}
