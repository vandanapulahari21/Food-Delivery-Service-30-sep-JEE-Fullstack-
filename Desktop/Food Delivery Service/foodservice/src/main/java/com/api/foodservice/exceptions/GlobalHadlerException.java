package com.api.foodservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalHadlerException 
{
	 @ExceptionHandler(CustomerNotFoundException.class)
	 public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) 
	 {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 @ExceptionHandler(CustomerDeletionException.class)
	 public ResponseEntity<?> handleCustomerDeletionException(CustomerDeletionException ex) 
	 {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(OrderNotFoundException.class)
	 public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) 
	 {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(InvalidOrderStatusException.class)
	 public ResponseEntity<String> handleInvalidOrderStatusException(InvalidOrderStatusException ex) 
	 {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<String> handleGeneralException(Exception ex) 
	 {
	        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 @ExceptionHandler(DeliveryDriverIdNotFoundException.class)
	 public ResponseEntity<String> handleDeliveryDriverIdNotFound(DeliveryDriverIdNotFoundException ex)
	 {
	    
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	 }
	 @ExceptionHandler(RestaurantNotFoundException.class)
	 public ResponseEntity<?> sendNotFoundStatus() {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	 @ExceptionHandler(MenuItemNotFoundException.class)
	    public ResponseEntity<String> handleException(Exception e) {
	        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
}
