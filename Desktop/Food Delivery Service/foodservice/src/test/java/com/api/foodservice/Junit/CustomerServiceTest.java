package com.api.foodservice.Junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.exceptions.CustomerDeletionException;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.OrdersRepository;
import com.api.foodservice.service.CustomerService;
import com.api.foodservice.service.OrdersService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private OrdersRepository orderRepository;

    @InjectMocks
    private CustomerService customerService;
    
    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("John Doe");
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();
        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomerById_CustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1);
        assertEquals(customer, result);
    }

    @Test
    public void testGetCustomerById_CustomerNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1));
    }

    @Test
    public void testUpdateCustomer_CustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        boolean result = customerService.updateCustomer(customer);
        assertTrue(result);
    }

    @Test
    public void testUpdateCustomer_CustomerNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customer));
    }

    @Test
    public void testAddCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        boolean result = customerService.addCustomer(customer);
        assertTrue(result);
    }
    
    @Test
    void testDeleteCustomerById_Success() {
        int customerId = 1;
        Customer customer = new Customer();
        Orders order = new Orders();
        customer.setOrders(Collections.singletonList(order));

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        boolean result = customerService.deleteCustomerById(customerId);

        verify(orderRepository).save(order);
        verify(customerRepository).delete(customer);
        assertTrue(result);
    }
    
    @Test
    void testDeleteCustomerById_CustomerNotFound() {
        int customerId = 1;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        boolean result = customerService.deleteCustomerById(customerId);

        verify(orderRepository, never()).save(any(Orders.class));
        verify(customerRepository, never()).delete(any(Customer.class));
        assertFalse(result);
    }
}
