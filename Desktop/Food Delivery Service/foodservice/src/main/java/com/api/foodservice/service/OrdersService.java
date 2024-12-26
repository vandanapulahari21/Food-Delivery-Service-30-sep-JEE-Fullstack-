package com.api.foodservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.DeliveryDrivers;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.exceptions.InvalidOrderStatusException;
import com.api.foodservice.exceptions.OrderNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.DeliveryDriverRepository;
import com.api.foodservice.repository.OrdersRepository;
import com.api.foodservice.repository.RestaurantsRepository;

@Service
public class OrdersService 
{
	@Autowired
	OrdersRepository orderRepository;
	
	@Autowired
	CustomerRepository customersRepository;
	
	@Autowired
    private DeliveryDriverRepository deliveryDriversRepository;
	
	
	@Transactional(readOnly = true)
	public List<Orders> getOrdersByCustomerId(int customerId) {
	    boolean customerExists = customersRepository.existsById(customerId);
	    
	    if (!customerExists) {
	        throw new CustomerNotFoundException(customerId);
	    }
	    
	    return orderRepository.findByCustomerCustomerId(customerId);
	}
	
	@Transactional
	public Orders placeOrder(Orders order) 
	{
		return orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	public Orders getOrderDetails(int orderId) 
	{
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }
	
	@Transactional
	public boolean updateOrderStatus(int orderId, String orderStatus) 
	{
        if (!orderStatus.equals("Pending") && !orderStatus.equals("Delivered") && !orderStatus.equals("Cancelled")) 
        {
            throw new InvalidOrderStatusException(orderStatus);
        }
 
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return true;
    }
	/*
	@Transactional
	public boolean cancelOrder(int orderId) 
	{
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.delete(order);
        return true;
    }
	*/
	public boolean deleteOrderById(int orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }


	
	
	public boolean assignDriverToOrder(int orderId, int driverId)
    {
        Orders order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return false;
        }
        DeliveryDrivers driver = deliveryDriversRepository.findById(driverId).orElse(null);
        if (driver == null) {
            return false;
        }
 
        order.setDeliveryDriver(driver);
 
        orderRepository.save(order);
        return true;
    }
	
	@Transactional(readOnly=true)
    public List<Orders> getOrdersByDriver(int driverId)
    {
        return orderRepository.findByDeliveryDriverDriverId(driverId);
    }  
	
	
}
