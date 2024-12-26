package com.api.foodservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.foodservice.entity.DeliveryAddresses;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.service.RestaurantsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@Tag(name="Restaurant Management")
@RequestMapping("/restaurants")
public class RestaurantController 
{
	@Autowired
    private RestaurantsService restaurantService;
    
	@GetMapping(produces="application/json")
    public ResponseEntity<List<Restaurants>> getAllRestaurants() {
         return new ResponseEntity<List<Restaurants>>(restaurantService.getAllRestaurants(),HttpStatus.OK);       
    }
    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantById(@PathVariable int restaurantId) {
        try {
            Restaurants restaurant = restaurantService.getRestaurantById(restaurantId);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RestaurantNotFoundException ex) {
        	System.out.println(ex.getMessage());
            return new ResponseEntity<>("Restaurant with ID " + restaurantId + " not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{restaurantName}")
    public ResponseEntity<?> getRestaurantByName(@PathVariable String restaurantName) {
        try {
            Restaurants restaurant = restaurantService.getRestaurantByName(restaurantName);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RestaurantNotFoundException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>("Restaurant with name '" + restaurantName + "' not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value="/{restaurantId}",consumes="application/json")
    public HttpStatus updateRestaurant(@RequestBody Restaurants restaurantDetails) {
        return restaurantService.updateRestaurant(restaurantDetails) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
    }
 
    @PostMapping(consumes="application/json")
    public HttpStatus addRestaurant(@RequestBody Restaurants restaurantDetails) {
        return restaurantService.createRestaurant(restaurantDetails) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
    }
    
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{restaurantId}/menu-items")
    public ResponseEntity<List<MenuItems>> getMenuItems(@PathVariable int restaurantId) {
        return new ResponseEntity<List<MenuItems>>(restaurantService.getMenuItemsByRestaurant(restaurantId),HttpStatus.OK);
    }
    
    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<List<String>> getReviewsByRestaurant(@PathVariable int restaurantId) {
        return new ResponseEntity<List<String>>(restaurantService.getReviewsByRestaurantId(restaurantId),HttpStatus.OK);
        
    }
    
    @GetMapping(value="/{restaurantId}/delivery-areas")
	public ResponseEntity<List<DeliveryAddresses>> getDeliveryAddressByRestaurantID(@PathVariable("restaurantId") int restaurantId) {
		List<DeliveryAddresses> list = restaurantService.getDeliveryAddressesOfSpecificRestaurant(restaurantId);
		return new ResponseEntity<List<DeliveryAddresses>>(list, HttpStatus.OK);
	}

}
