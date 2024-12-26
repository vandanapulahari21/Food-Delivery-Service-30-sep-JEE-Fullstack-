package com.api.foodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.foodservice.entity.Orders;
import com.api.foodservice.service.DeliveryDriverService;
import com.api.foodservice.service.OrdersService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="orders Management")
@RequestMapping("/orders")
public class OrderController 
{
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	DeliveryDriverService deliveryDriverService;
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<String> placeOrder(@RequestBody Orders order) {
	    ordersService.placeOrder(order);
	    return ResponseEntity.ok("Order placed successfully");
	}
	@GetMapping(value = "/{orderId}", produces = "application/json")
	public ResponseEntity<Orders> getOrderDetails(@PathVariable int orderId)
	{
        Orders order = ordersService.getOrderDetails(orderId);
        return ResponseEntity.ok(order);
    }
	
	@PutMapping(value = "/{orderId}/status", produces = "application/json")
	public HttpStatus updateOrderStatus(@PathVariable int orderId, @RequestParam String orderStatus) {
	    return ordersService.updateOrderStatus(orderId, orderStatus)?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value = "/{orderId}", produces = "application/json")
	public HttpStatus deleteOrderById(@PathVariable int orderId) {
		return ordersService.deleteOrderById(orderId)? HttpStatus.OK : HttpStatus.NOT_FOUND;
	    
	}
	
	@PutMapping("/{orderId}/assignDriver/{driverId}")
    public ResponseEntity<String> assignDriverToOrder(@PathVariable("orderId") int orderId,
    		                                 @PathVariable("driverId") int driverId)
	{
        boolean isAssigned = ordersService.assignDriverToOrder(orderId, driverId);
        if (isAssigned) {
            return ResponseEntity.ok("Driver assigned to the order successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to assign driver to the order.");
        }
    }

}
