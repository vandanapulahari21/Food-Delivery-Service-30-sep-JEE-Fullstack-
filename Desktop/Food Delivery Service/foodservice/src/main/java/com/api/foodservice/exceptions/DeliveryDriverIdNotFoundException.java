package com.api.foodservice.exceptions;

public class DeliveryDriverIdNotFoundException extends RuntimeException
{
	int driverId;
	public DeliveryDriverIdNotFoundException(String message)
	{
        super(message);
    }
    public DeliveryDriverIdNotFoundException(int driverId)
    {
        super("Delivery Driver with ID " + driverId + " not found.");
    }
	public Integer getDriverId() {
		
		return driverId;
	}

}
