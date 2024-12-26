package com.api.foodservice.Junit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.MenuItemNotFoundException;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.repository.MenuItemRepository;
import com.api.foodservice.repository.RestaurantsRepository;
import com.api.foodservice.service.MenuItemService;
@ExtendWith(MockitoExtension.class)
public class MenuItemServiceTest {
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private RestaurantsRepository restaurantRepository;
    @InjectMocks
    private MenuItemService menuItemService;
    private MenuItems menuItem;
    private Restaurants restaurant;
    @BeforeEach
    public void setUp() {
        restaurant = new Restaurants();
        restaurant.setRestaurantId(1);
        restaurant.setRestaurantName("Test Restaurant");
        menuItem = new MenuItems();
        menuItem.setItemId(1);
        menuItem.setItemName("Test Item");
        menuItem.setRestaurant(restaurant);
        // Set other properties as needed
    }
    @Test
    public void testGetAllMenuItems_MenuItemsExist() {
        List<MenuItems> menuItems = Arrays.asList(menuItem);
        when(menuItemRepository.findByRestaurant_RestaurantId(1)).thenReturn(menuItems);

        List<MenuItems> result = menuItemService.getAllMenuItems(1);
        assertEquals(menuItems, result);
    }
    @Test
    public void testGetAllMenuItems_MenuItemsNotFound() {
        when(menuItemRepository.findByRestaurant_RestaurantId(1)).thenReturn(Arrays.asList());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.getAllMenuItems(1));
    }
    @Test
    public void testAddMenuItem() {
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        MenuItems result = menuItemService.addMenuItem(menuItem);
        assertEquals(menuItem, result);
    }
    @Test
    public void testUpdateMenuItem_MenuItemExists() {
        when(menuItemRepository.findByItemIdAndRestaurant_RestaurantId(1, 1)).thenReturn(Optional.of(menuItem));
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        boolean result = menuItemService.updateMenuItem(menuItem);
        assertTrue(result);
    }
    @Test
    public void testUpdateMenuItem_MenuItemNotFound() {
        when(menuItemRepository.findByItemIdAndRestaurant_RestaurantId(1, 1)).thenReturn(Optional.empty());

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.updateMenuItem(menuItem));
    }
    @Test
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