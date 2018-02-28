package com.example.barclays.inventory_management.exceptions;
/**
 * @author Ted Tapar
 * Exception class to throws exception when more then available quantity is requested
 */

public class NotEnoughQuantityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotEnoughQuantityException(String itemName) {
		
		super(itemName + "Do not have enough quantity !");
	}
}
