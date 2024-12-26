package com.api.foodservice.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer>
{
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM favorite WHERE customer_id = :customerId AND restaurant_id = :restaurantId", nativeQuery = true)
	void removeFavorite(@Param("customerId") int customerId, @Param("restaurantId") int restaurantId);
}
