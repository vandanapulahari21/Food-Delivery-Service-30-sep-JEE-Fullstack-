package com.api.foodservice.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.service.DeliveryDriverService;
import com.api.foodservice.service.OrdersService;
public class OrdersControllerTest {
    @InjectMocks
    private OrderController orderController;
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrdersService ordersService;

    @Mock
    private DeliveryDriverService deliveryDriverService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        Orders order = new Orders();

        ResponseEntity<String> response = orderController.placeOrder(order);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order placed successfully", response.getBody());
        verify(ordersService, times(1)).placeOrder(order);
    }

    @Test
    public void testGetOrderDetails() {
        int orderId = 1;
        Orders order = new Orders();

        when(ordersService.getOrderDetails(orderId)).thenReturn(order);

        ResponseEntity<Orders> response = orderController.getOrderDetails(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(ordersService, times(1)).getOrderDetails(orderId);
    }

   /* @Test
    public void testUpdateOrderStatus() {
        int orderId = 1;
        String orderStatus = "Delivered";
        Orders updatedOrder = new Orders();

        when(ordersService.updateOrderStatus(orderId, orderStatus)).thenReturn(updatedOrder);

        ResponseEntity<Orders> response = orderController.updateOrderStatus(orderId, orderStatus);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedOrder, response.getBody());
        verify(ordersService, times(1)).updateOrderStatus(orderId, orderStatus);
    }
    */
    @Test
    public void testUpdateOrderStatus_Success() throws Exception {
        int orderId = 1;
        String orderStatus = "Delivered";

        when(ordersService.updateOrderStatus(orderId, orderStatus)).thenReturn(true);

        mockMvc.perform(put("/orders/{orderId}/status", orderId)
                .param("orderStatus", orderStatus))
                .andExpect(status().isOk());

        verify(ordersService, times(1)).updateOrderStatus(orderId, orderStatus);
    }

    @Test
    void testDeleteOrderById() throws Exception {
        int orderId = 1;

        when(ordersService.deleteOrderById(orderId)).thenReturn(true);

        mockMvc.perform(delete("/api/{orderId}", orderId))
               .andExpect(status().isOk());

        when(ordersService.deleteOrderById(orderId)).thenReturn(false);

        mockMvc.perform(delete("/api/{orderId}", orderId))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testAssignDriverToOrder() {
        int orderId = 1;
        int driverId = 1;

        when(ordersService.assignDriverToOrder(orderId, driverId)).thenReturn(true);

        ResponseEntity<String> response = orderController.assignDriverToOrder(orderId, driverId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver assigned to the order successfully.", response.getBody());
        verify(ordersService, times(1)).assignDriverToOrder(orderId, driverId);
    }

    @Test
    public void testAssignDriverToOrder_Failed() {
        int orderId = 1;
        int driverId = 1;

        when(ordersService.assignDriverToOrder(orderId, driverId)).thenReturn(false);

        ResponseEntity<String> response = orderController.assignDriverToOrder(orderId, driverId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to assign driver to the order.", response.getBody());
        verify(ordersService, times(1)).assignDriverToOrder(orderId, driverId);
    }
}
