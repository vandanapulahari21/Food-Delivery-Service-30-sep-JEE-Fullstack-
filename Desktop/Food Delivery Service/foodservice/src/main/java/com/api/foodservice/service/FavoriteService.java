package com.api.foodservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.Favorite;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.FavoriteRepository;
import com.api.foodservice.repository.RestaurantsRepository;

@Service
public class FavoriteService 
{

	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantsRepository restaurantRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Transactional
    public void addFavoriteRestaurant(int customerId, int restaurantId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        Restaurants restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Favorite favorite = new Favorite();
        favorite.setCustomers(customer);
        favorite.setRestaurants(restaurant);

        favoriteRepository.save(favorite);
    }
    @Transactional
    public void removeFavoriteRestaurant(int customerId, int restaurantId) {
        favoriteRepository.removeFavorite(customerId, restaurantId);
    }
}
