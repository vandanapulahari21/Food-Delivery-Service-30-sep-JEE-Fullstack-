package com.api.foodservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.exceptions.MenuItemNotFoundException;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.repository.MenuItemRepository;
import com.api.foodservice.repository.RestaurantsRepository;

@Service
public class MenuItemService 
{
	@Autowired
    private MenuItemRepository menuItemRepository;
 
    @Autowired
    private RestaurantsRepository restaurantRepository;
 
    @Transactional(readOnly = true)
    public List<MenuItems> getAllMenuItems(Integer restaurantId) {
        List<MenuItems> menuItems = menuItemRepository.findByRestaurant_RestaurantId(restaurantId);
        if (menuItems.isEmpty()) {
            throw new MenuItemNotFoundException(restaurantId);
        }
        return menuItems;
    }
    
    @Transactional
    public MenuItems addMenuItem(MenuItems menuItems)
    {
    	return menuItemRepository.save(menuItems);
    }
   
    
    @Transactional
	public boolean updateMenuItem(MenuItems menuItems) throws MenuItemNotFoundException
	{
    	Integer itemId = menuItems.getItemId();
        Integer restaurantId = menuItems.getRestaurant().getRestaurantId();

        Optional<MenuItems> existingMenuItem = menuItemRepository.findByItemIdAndRestaurant_RestaurantId(itemId, restaurantId);
        if(existingMenuItem.isPresent())
        	 return menuItemRepository.save(menuItems)!=null;
        else
        	throw new MenuItemNotFoundException(itemId,restaurantId);

	}
   /*
    @Transactional
    public boolean deleteMenuItem(int itemId) {
        long count = menuItemRepository.count();
        try {
            menuItemRepository.deleteById(itemId);
        } catch (Exception e) {
            return false;
        }
        return menuItemRepository.count() < count;
    }
    */
    public void deleteMenuItemFromRestaurant(int restaurantId, int itemId) {
    	 
	    Restaurants restaurant = restaurantRepository.findById(restaurantId).orElse(null);
	    if (restaurant == null) {
	        throw new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId);
	    }
 
	    MenuItems currItem = menuItemRepository.findById(itemId).orElse(null);
	    if (currItem == null || !restaurant.getMenuItems().contains(currItem)) {
	        throw new MenuItemNotFoundException("Menu item doesn't exist with id: " + itemId);
	    }
 
	    restaurant.getMenuItems().remove(currItem);
	    menuItemRepository.delete(currItem);
 
	}

}
