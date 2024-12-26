package com.api.foodservice.controller;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.service.CustomerService;
import com.api.foodservice.service.FavoriteService;
import com.api.foodservice.service.OrdersService;
import com.api.foodservice.service.RatingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private RatingsService ratingsService;

    @MockBean
    private FavoriteService favoriteService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(customers.size()));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        int customerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        when(customerService.getCustomerById(customerId)).thenReturn(customer);

        mockMvc.perform(get("/customers/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(true);

        mockMvc.perform(put("/customers/{customerId}", customer.getCustomerId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        when(customerService.addCustomer(any(Customer.class))).thenReturn(true);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCustomerById() throws Exception {
        int customerId = 1;
        when(customerService.deleteCustomerById(customerId)).thenReturn(true);

        mockMvc.perform(delete("/customers/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrdersByCustomerId() throws Exception {
        int customerId = 1;
        List<Orders> orders = Arrays.asList(new Orders(), new Orders());
        when(ordersService.getOrdersByCustomerId(customerId)).thenReturn(orders);

        mockMvc.perform(get("/customers/{customerId}/orders", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(orders.size()));
    }

    @Test
    public void testGetReviewsByCustomerId() throws Exception {
        int customerId = 1;
        List<String> reviews = Arrays.asList("Review 1", "Review 2");
        when(ratingsService.getReviewsByCustomerId(customerId)).thenReturn(reviews);

        mockMvc.perform(get("/customers/{customerId}/reviews", customerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(reviews.size()));
    }
    @Test
    public void testAddFavoriteRestaurant() throws Exception {
        doNothing().when(favoriteService).addFavoriteRestaurant(anyInt(), anyInt());

        mockMvc.perform(post("/customers/1/favorites")
                .param("restaurantId", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer's favorite restaurants updated successfully."));
    }

    @Test
    public void testRemoveFavoriteRestaurant() throws Exception {
        doNothing().when(favoriteService).removeFavoriteRestaurant(anyInt(), anyInt());

        mockMvc.perform(delete("/customers/1/favorites/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer's favorite restaurant removed successfully."));
    }
    /*
    @Test
    public void testAddFavoriteRestaurant() throws Exception {
        int customerId = 1;
        int restaurantId = 1;
        doNothing().when(favoriteService).addFavoriteRestaurant(customerId, restaurantId);

        mockMvc.perform(post("/customers/{customerId}/favorites", customerId)
                .param("restaurantId", String.valueOf(restaurantId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Customer's favorite restaurant added successfully."));
    }

    @Test
    public void testRemoveFavoriteRestaurant() throws Exception {
        int customerId = 1;
        int restaurantId = 1;
        doNothing().when(favoriteService).removeFavoriteRestaurant(customerId, restaurantId);

        mockMvc.perform(delete("/customers/{customerId}/favorites/{restaurantId}", customerId, restaurantId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Customer's favorite restaurant removed successfully."));
    }
    */
}