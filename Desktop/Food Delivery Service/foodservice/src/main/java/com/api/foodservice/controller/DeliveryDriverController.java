package com.api.foodservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.foodservice.entity.DeliveryDrivers;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.exceptions.CustomException;
import com.api.foodservice.exceptions.DeliveryDriverIdNotFoundException;
import com.api.foodservice.service.DeliveryDriverService;
import com.api.foodservice.service.OrdersService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Delivery Drivers")
@RequestMapping("/drivers")
public class DeliveryDriverController 
{
	@Autowired
	DeliveryDriverService deliveryDriversService;
	
	@Autowired
	OrdersService ordersService;
	
    @GetMapping
	public ResponseEntity<List<DeliveryDrivers>> getAllDeliveryDrivers() throws CustomException
	{
		List<DeliveryDrivers> deliveryDrivers=deliveryDriversService.getAllDeliveryDrivers();
		if(deliveryDrivers==null || deliveryDrivers.isEmpty())
		{
   		throw new CustomException("Delivery Drivers not available");	
		}
		else
		{
			return new ResponseEntity<List<DeliveryDrivers>>(deliveryDrivers,HttpStatus.OK);
		}
	}
	
    @GetMapping("/{driverId}")
	public ResponseEntity<DeliveryDrivers> getDeliveryDriversById(@PathVariable("driverId") int driver_id)
			                                throws DeliveryDriverIdNotFoundException
    {
		DeliveryDrivers deliveryDrivers=deliveryDriversService.getDeliveryDriversById(driver_id);
		if(deliveryDrivers==null)
		{
			throw new DeliveryDriverIdNotFoundException(driver_id);
        }
		return new ResponseEntity<DeliveryDrivers>(deliveryDrivers,HttpStatus.OK);
	}
    /*
    @GetMapping("/{driverId}/orders")
	public ResponseEntity<List<Orders>> getOrdersByDriver(@PathVariable("driverId")int driver_id)
			           throws DeliveryDriverIdNotFoundException
	{
		List<Orders> orders = ordersService.getOrdersByDriver(driver_id);
		if(orders ==null)
		{
			throw new DeliveryDriverIdNotFoundException(driver_id);
		}
		else
		{
			return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
		}
	}
	*/
    @GetMapping("/{driverId}/orders")
    public ResponseEntity<List<Orders>> getOrdersByDriver(@PathVariable("driverId") int driver_id)
               throws DeliveryDriverIdNotFoundException {
        List<Orders> orders = ordersService.getOrdersByDriver(driver_id);
        if (orders == null) {
            throw new DeliveryDriverIdNotFoundException(driver_id);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

}
