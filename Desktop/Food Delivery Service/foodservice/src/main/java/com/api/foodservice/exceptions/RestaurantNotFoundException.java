package com.api.foodservice.exceptions;

public class RestaurantNotFoundException extends RuntimeException 
{
    public RestaurantNotFoundException() {}
	public RestaurantNotFoundException(int restaurantId) {
		super("Restaurant with ID " + restaurantId + " not found");
    }
	public RestaurantNotFoundException(String message)
	{
		super(message);
	}
	@Override
	    public String toString() {
	        return "RestaurantNotFoundException: " + getMessage();
	    }
	
}
