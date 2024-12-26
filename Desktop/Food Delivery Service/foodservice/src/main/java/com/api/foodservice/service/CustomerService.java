package com.api.foodservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.DeliveryAddresses;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.exceptions.CustomerDeletionException;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.exceptions.MenuItemNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.OrdersRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrdersRepository orderRepository;
	
	
	@Transactional(readOnly =true)
	public List<Customer> getAllCustomers()
	{
		return customerRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Customer getCustomerById(int customerId) {
	    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
	    
	    if (optionalCustomer.isPresent()) {
	        return optionalCustomer.get();
	    } else {
	        throw new CustomerNotFoundException(customerId);
	    }
	}
	 
	public boolean updateCustomer(Customer customerDetails) throws CustomerNotFoundException
	{
    	int customerId = customerDetails.getCustomerId();
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if(existingCustomer.isPresent())
        	 return customerRepository.save(customerDetails)!=null;
        else
        	throw new CustomerNotFoundException(customerId);

	}
	@Transactional(readOnly=true)
	public int getMaxCustomerId()
	{
		return customerRepository.findMaxCustomerId();
	}
	@Transactional
	public boolean addCustomer(Customer customerDetails) throws CustomerNotFoundException
	{
		customerDetails.setCustomerId(getMaxCustomerId()+1);
		 return customerRepository.save(customerDetails)!=null;
	}
	
	
	@Transactional
    public boolean deleteCustomerById(int customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            for (Orders order : customer.getOrders()) {
                order.setCustomer(null);
                orderRepository.save(order);
            }

            customerRepository.delete(customer);
            return true;
        } else {
            return false;
        }
    }
}
