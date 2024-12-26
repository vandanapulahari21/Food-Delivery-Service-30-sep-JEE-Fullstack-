package com.api.foodservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.foodservice.entity.MenuItems;

public interface MenuItemRepository extends JpaRepository<MenuItems,Integer>
{
	@Query("SELECT m FROM MenuItems m WHERE m.restaurant.restaurantId = :restaurantId")
	List<MenuItems> findByRestaurantId(@Param("restaurantId") int restaurantId);
	
	List<MenuItems> findByRestaurant_RestaurantId(Integer restaurantId);
    Optional<MenuItems> findByItemIdAndRestaurant_RestaurantId(Integer itemId, Integer restaurantId);

}
