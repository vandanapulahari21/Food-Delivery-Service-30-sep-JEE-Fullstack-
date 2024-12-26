package com.api.foodservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.DeliveryAddresses;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.repository.MenuItemRepository;
import com.api.foodservice.repository.RatingsRepository;
import com.api.foodservice.repository.RestaurantsRepository;

@Service
public class RestaurantsService 
{
	@Autowired
	RestaurantsRepository restaurantRepository;
	
	@Autowired
    MenuItemRepository menuItemRepository;
	
    @Autowired
    RatingsRepository ratingRepository;
   
 
 
    @Transactional(readOnly = true)
    public Restaurants getRestaurantById(int restaurantId) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }
    
    @Transactional(readOnly = true)
    public List<Restaurants> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    
    @Transactional
    public boolean createRestaurant(Restaurants restaurant) throws RestaurantNotFoundException{
        return restaurantRepository.save(restaurant)!=null;
    }
 
    @Transactional
    public boolean updateRestaurant(Restaurants restaurantDetails) throws RestaurantNotFoundException {
        return restaurantRepository.save(restaurantDetails)!=null;
    }
    /*
    @Transactional
    public boolean deleteRestaurant(int restaurantId) throws RestaurantNotFoundException {
    	long count = restaurantRepository.count();
        restaurantRepository.deleteById(restaurantId);
        return restaurantRepository.count() < count;
    }
    */
    
	// method to delete the specific restaurant
    @Transactional
    public void deleteRestaurantById(int restaurantId) {
        try {
            Restaurants restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
            restaurantRepository.delete(restaurant);
            System.out.println("Restaurant with ID " + restaurantId + " and its orders deleted successfully.");
        } catch (RestaurantNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error deleting restaurant: " + e.getMessage());
        }
    }
    @Transactional(readOnly = true)
    public Restaurants getRestaurantByName(String restaurantName) throws RestaurantNotFoundException {
        return restaurantRepository.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantName));
    }
    @Transactional(readOnly = true)
    public List<MenuItems> getMenuItemsByRestaurant(int restaurantId) throws RestaurantNotFoundException {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
    @Transactional(readOnly = true)
    public List<String> getReviewsByRestaurantId(int restaurantId) throws RestaurantNotFoundException{
        return ratingRepository.findReviewsByRestaurantId(restaurantId);
    }
	
    @Transactional(readOnly = true)
    public List<DeliveryAddresses> getDeliveryAddressesOfSpecificRestaurant(int restaurantId) {
		List<DeliveryAddresses> addresses = new ArrayList<>();		
		Restaurants restaurant = restaurantRepository.findById(restaurantId).orElse(null);	
		List<Orders> orders = restaurant.getOrders();	
		for(Orders order : orders) {
			for(DeliveryAddresses address : order.getCustomer().getDeliveryAddresses()) addresses.add(address);
		}	
		return addresses;
	}

}
