package com.api.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.foodservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> 
{
	@Query(nativeQuery=true,value="select max(customer_id) from customers")
	int findMaxCustomerId();
}
