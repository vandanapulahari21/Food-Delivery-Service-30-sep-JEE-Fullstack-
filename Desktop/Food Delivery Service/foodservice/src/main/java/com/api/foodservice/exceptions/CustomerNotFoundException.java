package com.api.foodservice.exceptions;

public class CustomerNotFoundException extends RuntimeException 
{
	public CustomerNotFoundException(int customerId) {
        super("Customer with ID " + customerId + " not found.");
    }

}