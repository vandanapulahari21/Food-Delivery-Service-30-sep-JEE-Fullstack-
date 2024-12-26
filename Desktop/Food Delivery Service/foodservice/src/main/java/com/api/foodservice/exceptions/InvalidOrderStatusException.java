package com.api.foodservice.exceptions;
public class InvalidOrderStatusException extends RuntimeException
{
	public InvalidOrderStatusException(String status)
	{
	    super("Invalid order status: " + status);
	}

}
