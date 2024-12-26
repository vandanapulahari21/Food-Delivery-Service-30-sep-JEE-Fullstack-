package com.api.foodservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.foodservice.entity.DeliveryAddresses;
import com.api.foodservice.entity.Restaurants;

public interface RestaurantsRepository extends JpaRepository<Restaurants,Integer> 
{
	Optional<Restaurants> findByRestaurantName(String restaurantName);
}
