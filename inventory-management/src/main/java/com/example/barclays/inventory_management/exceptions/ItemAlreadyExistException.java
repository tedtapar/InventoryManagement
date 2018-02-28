package com.example.barclays.inventory_management.exceptions;

public class ItemAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistException(String itemName){
		super(itemName + " Already exist in the inventory");
	}
}
