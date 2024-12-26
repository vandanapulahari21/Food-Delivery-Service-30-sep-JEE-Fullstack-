package com.api.foodservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.foodservice.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Integer>
{
	List<Orders> findByCustomerCustomerId(int customerId);
	
	Optional<Orders> findById(int orderId);
	List<Orders> findByDeliveryDriverDriverId(int driverId);
}
