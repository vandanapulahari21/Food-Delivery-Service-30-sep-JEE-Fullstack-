package com.api.foodservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.foodservice.entity.Ratings;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
	
	@Query("SELECT r.review FROM Ratings r WHERE r.order.customer.customerId = :customerId")
    List<String> findReviewsByCustomerId(@Param("customerId") int customerId);
	
	@Query("SELECT r.review FROM Ratings r WHERE r.restaurant.restaurantId = :restaurantId")
    List<String> findReviewsByRestaurantId(@Param("restaurantId") int restaurantId);
	
}