package com.api.foodservice.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.exceptions.RestaurantNotFoundException;
import com.api.foodservice.service.RestaurantsService;

public class RestaurantContollerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantsService restaurantService;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurants> restaurants = Arrays.asList(new Restaurants(), new Restaurants());

        when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        ResponseEntity<List<Restaurants>> response = restaurantController.getAllRestaurants();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    public void testGetRestaurantById() {
        int restaurantId = 1;
        Restaurants restaurant = new Restaurants();

        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);

        ResponseEntity<?> response = restaurantController.getRestaurantById(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
        verify(restaurantService, times(1)).getRestaurantById(restaurantId);
    }

    @Test
    public void testGetRestaurantById_NotFound() {
        int restaurantId = 1;

        when(restaurantService.getRestaurantById(restaurantId)).thenThrow(new RestaurantNotFoundException(restaurantId));

        ResponseEntity<?> response = restaurantController.getRestaurantById(restaurantId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Restaurant with ID " + restaurantId + " not found", response.getBody());
        verify(restaurantService, times(1)).getRestaurantById(restaurantId);
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurants restaurant = new Restaurants();

        when(restaurantService.updateRestaurant(restaurant)).thenReturn(true);

        HttpStatus response = restaurantController.updateRestaurant(restaurant);

        assertEquals(HttpStatus.OK, response);
        verify(restaurantService, times(1)).updateRestaurant(restaurant);
    }

    @Test
    public void testUpdateRestaurant_NotModified() {
        Restaurants restaurant = new Restaurants();

        when(restaurantService.updateRestaurant(restaurant)).thenReturn(false);

        HttpStatus response = restaurantController.updateRestaurant(restaurant);

        assertEquals(HttpStatus.NOT_MODIFIED, response);
        verify(restaurantService, times(1)).updateRestaurant(restaurant);
    }

    @Test
    public void testAddRestaurant() {
        Restaurants restaurant = new Restaurants();

        when(restaurantService.createRestaurant(restaurant)).thenReturn(true);

        HttpStatus response = restaurantController.addRestaurant(restaurant);

        assertEquals(HttpStatus.OK, response);
        verify(restaurantService, times(1)).createRestaurant(restaurant);
    }

    @Test
    public void testAddRestaurant_NotModified() {
        Restaurants restaurant = new Restaurants();

        when(restaurantService.createRestaurant(restaurant)).thenReturn(false);

        HttpStatus response = restaurantController.addRestaurant(restaurant);

        assertEquals(HttpStatus.NOT_MODIFIED, response);
        verify(restaurantService, times(1)).createRestaurant(restaurant);
    }

  /*  @Test
    void testDeleteRestaurant() throws Exception {
        int restaurantId = 1;

        doNothing().when(restaurantService).deleteRestaurantById(restaurantId);

        mockMvc.perform(delete("/api/{restaurantId}", restaurantId))
               .andExpect(status().isOk());
    }*/

    @Test
    public void testGetMenuItems() {
        int restaurantId = 1;
        List<MenuItems> menuItems = Arrays.asList(new MenuItems(), new MenuItems());

        when(restaurantService.getMenuItemsByRestaurant(restaurantId)).thenReturn(menuItems);

        ResponseEntity<List<MenuItems>> response = restaurantController.getMenuItems(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(restaurantService, times(1)).getMenuItemsByRestaurant(restaurantId);
    }

    @Test
    public void testGetReviewsByRestaurant() {
        int restaurantId = 1;
        List<String> reviews = Arrays.asList("Review 1", "Review 2");

        when(restaurantService.getReviewsByRestaurantId(restaurantId)).thenReturn(reviews);

        ResponseEntity<List<String>> response = restaurantController.getReviewsByRestaurant(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(restaurantService, times(1)).getReviewsByRestaurantId(restaurantId);
    }
}
