package com.api.foodservice.Junit;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
import com.api.foodservice.entity.*;
import com.api.foodservice.exceptions.*;
import com.api.foodservice.repository.*;
import com.api.foodservice.service.OrdersService;
 
import java.util.*;
 
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrdersServiceTest {
 
    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private CustomerRepository customersRepository;
    @Mock
    private DeliveryDriverRepository deliveryDriverRepository;
 
    @InjectMocks
    private OrdersService ordersService;
 
    private Orders order;
    private Customer customer;
    private DeliveryDrivers driver;
    private List<Orders> ordersList;
 
    @BeforeEach
    public void setUp() {
        // Initialize mock objects
        order = new Orders();
        order.setOrderId(1);
        order.setOrderStatus("Pending");
 
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("John Doe");
 
        driver = new DeliveryDrivers();
        driver.setDriverId(1);
        driver.setDriverName("Driver A");
 
        ordersList = new ArrayList<>();
        ordersList.add(order);
    }
 
    @Test
    public void testGetOrdersByCustomerId() {
        when(customersRepository.existsById(1)).thenReturn(true);
        when(ordersRepository.findByCustomerCustomerId(1)).thenReturn(ordersList);
 
        List<Orders> orders = ordersService.getOrdersByCustomerId(1);
 
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals("Pending", orders.get(0).getOrderStatus());
    }
 
    @Test
    public void testGetOrdersByCustomerId_CustomerNotFound() {
        when(customersRepository.existsById(1)).thenReturn(false);
 
        assertThrows(CustomerNotFoundException.class, () -> {
            ordersService.getOrdersByCustomerId(1);
        });
    }
 
    @Test
    public void testPlaceOrder() {
        when(ordersRepository.save(order)).thenReturn(order);
 
        Orders placedOrder = ordersService.placeOrder(order);
 
        assertNotNull(placedOrder);
        assertEquals("Pending", placedOrder.getOrderStatus());
    }
 
    @Test
    public void testGetOrderDetails() {
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));
 
        Orders foundOrder = ordersService.getOrderDetails(1);
 
        assertNotNull(foundOrder);
        assertEquals(1, foundOrder.getOrderId());
    }
 
    @Test
    public void testGetOrderDetails_OrderNotFound() {
        when(ordersRepository.findById(1)).thenReturn(Optional.empty());
 
        assertThrows(OrderNotFoundException.class, () -> {
            ordersService.getOrderDetails(1);
        });
    }
    
    @Test
    public void testUpdateOrderStatus_Success() {
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));
        boolean result = ordersService.updateOrderStatus(1,"Delivered");
        assertEquals("Delivered", order.getOrderStatus());
    }
 
    @Test
    public void testUpdateOrderStatus_InvalidStatus() {
        assertThrows(InvalidOrderStatusException.class, () -> {
            ordersService.updateOrderStatus(1, "Shipped");
        });
    }
 
    @Test
    void testDeleteOrderById_Success() {
        int orderId = 1;

        when(ordersRepository.existsById(orderId)).thenReturn(true);

        boolean result = ordersService.deleteOrderById(orderId);

        verify(ordersRepository).deleteById(orderId);
        assertTrue(result);
    }
 
    @Test
    public void testAssignDriverToOrder() {
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));
        when(deliveryDriverRepository.findById(1)).thenReturn(Optional.of(driver));
 
        boolean result = ordersService.assignDriverToOrder(1, 1);
 
        assertTrue(result);
        assertEquals(driver, order.getDeliveryDriver());
        verify(ordersRepository, times(1)).save(order);
    }
 
    @Test
    public void testAssignDriverToOrder_OrderNotFound() {
        when(ordersRepository.findById(1)).thenReturn(Optional.empty());
 
        boolean result = ordersService.assignDriverToOrder(1, 1);
 
        assertFalse(result);
    }
 
    @Test
    public void testAssignDriverToOrder_DriverNotFound() {
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));
        when(deliveryDriverRepository.findById(1)).thenReturn(Optional.empty());
 
        boolean result = ordersService.assignDriverToOrder(1, 1);
 
        assertFalse(result);
    }
 
    @Test
    public void testGetOrdersByDriver() {
        when(ordersRepository.findByDeliveryDriverDriverId(1)).thenReturn(ordersList);
 
        List<Orders> result = ordersService.getOrdersByDriver(1);
 
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}