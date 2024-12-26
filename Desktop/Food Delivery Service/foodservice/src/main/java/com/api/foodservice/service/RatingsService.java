package com.api.foodservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.foodservice.entity.Ratings;
import com.api.foodservice.exceptions.CustomerNotFoundException;
import com.api.foodservice.repository.CustomerRepository;
import com.api.foodservice.repository.RatingsRepository;


import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Transactional(readOnly = true)
    public List<String> getReviewsByCustomerId(int customerId) {
        boolean customerExists = customerRepository.existsById(customerId);
        
        if (!customerExists) {
            throw new CustomerNotFoundException(customerId);
        }
        
        return ratingsRepository.findReviewsByCustomerId(customerId);
    }
}