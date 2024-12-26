package com.api.foodservice.exceptions;

public class MenuItemNotFoundException extends RuntimeException
{
	public MenuItemNotFoundException(int itemId) {
        super("Menu item not found.");
    }
	
	public MenuItemNotFoundException(int restaurantId,int itemId) 
	{
        super("Menu item with ID " + itemId + " not found in restaurant with " + restaurantId);
    }

	public MenuItemNotFoundException(String string) {
		
	}


}
