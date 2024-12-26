package com.api.foodservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.DeliveryDrivers;
import com.api.foodservice.repository.DeliveryDriverRepository;
import com.api.foodservice.repository.OrdersRepository;



@Service
public class DeliveryDriverService 
{
	@Autowired
	DeliveryDriverRepository deliveryDriversRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Transactional(readOnly=true)
	public List<DeliveryDrivers> getAllDeliveryDrivers()
	{
		return deliveryDriversRepository.findAll();
	}
	@Transactional(readOnly=true)
	public DeliveryDrivers getDeliveryDriversById(int driverId)
	{
		return deliveryDriversRepository.findById(driverId).orElse(null);
	}

}
