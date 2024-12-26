package com.api.foodservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.MenuItemNotFoundException;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.repository.MenuItemRepository;
import com.api.foodservice.repository.RestaurantsRepository;
import com.api.foodservice.service.MenuItemService;

import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@Tag(name="Menu Items")
@RequestMapping("/restaurants/{restaurantId}/menu")
public class MenuItemController 
{
	@Autowired
    private MenuItemService menuItemService;
    @Autowired
    MenuItemRepository menuItemRepository;
    
    @Autowired
    private RestaurantsRepository restaurantRepository;
    @GetMapping
    public ResponseEntity<List<MenuItems>> getAllMenuItems(@PathVariable Integer restaurantId) {
        List<MenuItems> menuItems = menuItemService.getAllMenuItems(restaurantId);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItems menuItems) {
        menuItemService.addMenuItem(menuItems);
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
 
    
    @PutMapping(value="/{itemId}",consumes="application/json")
    public HttpStatus updateMenuItem(@RequestBody MenuItems menuItems) 
    {
         return menuItemService.updateMenuItem(menuItems)?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        
    }
    /*
    @DeleteMapping(value="/{itemId}")
	public HttpStatus deleteMenuItem(@PathVariable int itemId)
	{
		return menuItemService.deleteMenuItem(itemId) ? HttpStatus.OK : HttpStatus.NOT_FOUND ;
	}
	*/
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Integer restaurantId, @PathVariable Integer itemId) {
        menuItemService.deleteMenuItemFromRestaurant(restaurantId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<String> handleMenuItemNotFound(MenuItemNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
