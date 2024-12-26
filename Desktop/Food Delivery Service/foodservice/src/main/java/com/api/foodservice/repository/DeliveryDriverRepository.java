package com.api.foodservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.foodservice.entity.DeliveryDrivers;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDrivers,Integer>
{
	Optional<DeliveryDrivers> findById(int driverId);

}
