package com.api.foodservice.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.api.foodservice.controller.DeliveryDriverController;
import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.DeliveryDrivers;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.entity.Restaurants;
import com.api.foodservice.service.CustomerService;
import com.api.foodservice.service.DeliveryDriverService;
import com.api.foodservice.service.OrdersService;
import com.api.foodservice.service.RestaurantsService;
import com.api.foodservice.exceptions.DeliveryDriverIdNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.DeliveryDriverRepository;
import com.api.foodservice.repository.OrdersRepository;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DeliveryDriverController.class)
public class DeliveryDriversControllerTest {
 
    @Autowired
    MockMvc mockMvc;
 
    @MockBean
    DeliveryDriverService deliveryDriverService;
    @MockBean
    OrdersService orderservice;
    @MockBean
    RestaurantsService restaurantService;
    @MockBean
    CustomerService customerService;
    Customer customer = new Customer();
    Restaurants restaurant = new Restaurants();
    DeliveryDrivers d1 = new DeliveryDrivers(101, "Ajay", "98766554", "1000");
    DeliveryDrivers d2 = new DeliveryDrivers(102, "Bharath", "7865436", "2800");
    Orders order = new Orders(1, LocalDateTime.now(), customer, restaurant, d1, "Pending");
    Orders order2 = new Orders(2, LocalDateTime.now(), customer, restaurant, d2, "Pending");
    @Test
    public void testGetMapping_getAllDeliveryDrivers() throws Exception {
        when(deliveryDriverService.getAllDeliveryDrivers()).thenReturn(Arrays.asList(d1, d2));
        mockMvc.perform(get("/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
 
    @Test
    public void testGetMapping_getDeliveryDriverById() throws Exception {
        when(deliveryDriverService.getDeliveryDriversById(101)).thenReturn(d1);
        mockMvc.perform(get("/drivers/{driverId}", 101))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.driverId").value(101));
    }
 
    @Test
    public void testGetMapping_getDeliveryDriverById_negative() throws Exception {
        when(deliveryDriverService.getDeliveryDriversById(103)).thenThrow(new DeliveryDriverIdNotFoundException(3));
        mockMvc.perform(get("/drivers/{driverId}", 103))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testGetMapping_getOrdersByDriver() throws Exception {
        when(orderservice.getOrdersByDriver(1)).thenReturn(Arrays.asList(order, order2));
        mockMvc.perform(get("/drivers/{driverId}/orders", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
 
    @Test
    public void testGetMapping_getOrdersByDriver_negative() throws Exception {
        when(orderservice.getOrdersByDriver(2)).thenReturn(null);
        mockMvc.perform(get("/drivers/{driverId}/orders", 2))
                .andExpect(status().isNotFound());
    }
}
