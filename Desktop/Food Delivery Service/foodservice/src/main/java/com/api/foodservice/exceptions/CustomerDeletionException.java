package com.api.foodservice.exceptions;

public class CustomerDeletionException extends RuntimeException 
{
	public CustomerDeletionException(int customerId) 
	{
        super("Failed to Delete Customer with "+customerId);
    }
}
