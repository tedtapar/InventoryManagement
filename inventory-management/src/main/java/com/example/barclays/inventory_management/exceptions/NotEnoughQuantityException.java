package com.example.barclays.inventory_management.exceptions;

public class NotEnoughQuantityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotEnoughQuantityException(String itemName) {
		
		super(itemName + "Do not have enough quantity !");
	}
}
