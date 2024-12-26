package com.api.foodservice.Junit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.repository.RestaurantsRepository;
import com.api.foodservice.repository.MenuItemRepository;
import com.api.foodservice.repository.RatingsRepository;
import com.api.foodservice.service.RestaurantsService;
 
@ExtendWith(MockitoExtension.class)
public class RestaurantsServiceTest {
 
    @Mock
    private RestaurantsRepository restaurantRepository;
 
    @Mock
    private MenuItemRepository menuItemRepository;
 
    @Mock
    private RatingsRepository ratingRepository;
 
    @InjectMocks
    private RestaurantsService restaurantsService;
 
    private Restaurants restaurant;
 
    @BeforeEach
    void setUp() {
        restaurant = new Restaurants();
        restaurant.setRestaurantId(1);
        restaurant.setRestaurantName("Tasty Bites");
        restaurant.setRestaurantAddress("123 Main St");
        restaurant.setRestaurantPhone("+1234567890");
    }
    @AfterEach
    void setUpabc() {
    	restaurant = null;
    }

 
    @Test
    void testGetRestaurantById_Success() throws RestaurantNotFoundException {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        Restaurants result = restaurantsService.getRestaurantById(1);
        assertEquals(restaurant, result);
    }
 
    @Test
    void testGetRestaurantById_NotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantsService.getRestaurantById(1);
        });
    }
 
    @Test
    void testGetAllRestaurants_Success() throws RestaurantNotFoundException {
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));
        List<Restaurants> result = restaurantsService.getAllRestaurants();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
 
    @Test
    void testCreateRestaurant_Success() throws RestaurantNotFoundException {
        when(restaurantRepository.save(Mockito.any(Restaurants.class))).thenReturn(restaurant);
        boolean result = restaurantsService.createRestaurant(restaurant);
        assertTrue(result);
    }
 
    @Test
    void testCreateRestaurant_Failure() throws RestaurantNotFoundException {
        when(restaurantRepository.save(Mockito.any(Restaurants.class))).thenReturn(null);
        boolean result = restaurantsService.createRestaurant(restaurant);
        assertFalse(result);
    }
 
    @Test
    void testUpdateRestaurant_Success() throws RestaurantNotFoundException {
        when(restaurantRepository.save(Mockito.any(Restaurants.class))).thenReturn(restaurant);
        boolean result = restaurantsService.updateRestaurant(restaurant);
        assertTrue(result);
    }
 
    @Test
    void testUpdateRestaurant_Failure() throws RestaurantNotFoundException {
        when(restaurantRepository.save(Mockito.any(Restaurants.class))).thenReturn(null);
        boolean result = restaurantsService.updateRestaurant(restaurant);
        assertFalse(result);
    }
    @Test
    void testGetMenuItemsByRestaurant_Success() throws RestaurantNotFoundException {
        List<MenuItems> menuItems = List.of(new MenuItems());
        when(menuItemRepository.findByRestaurantId(1)).thenReturn(menuItems);
        List<MenuItems> result = restaurantsService.getMenuItemsByRestaurant(1);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
 
    @Test
    void testGetReviewsByRestaurantId_Success() throws RestaurantNotFoundException {
        List<String> reviews = List.of("Great food!", "Excellent service.");
        when(ratingRepository.findReviewsByRestaurantId(1)).thenReturn(reviews);
        List<String> result = restaurantsService.getReviewsByRestaurantId(1);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
}
