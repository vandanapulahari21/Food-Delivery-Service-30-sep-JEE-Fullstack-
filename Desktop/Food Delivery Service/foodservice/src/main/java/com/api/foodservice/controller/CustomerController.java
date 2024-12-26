package com.api.foodservice.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.foodservice.entity.Customer;
import com.api.foodservice.entity.Orders;
import com.api.foodservice.entity.Ratings;
import com.api.foodservice.service.CustomerService;
import com.api.foodservice.service.FavoriteService;
import com.api.foodservice.service.OrdersService;
import com.api.foodservice.service.RatingsService;
import io.swagger.v3.oas.annotations.tags.Tag;
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@Tag(name="Customer Profile")
@RequestMapping("/customers")
public class CustomerController 
{
	@Autowired
	CustomerService customerService;

	@Autowired
	OrdersService orderService;
	
	@Autowired
    private RatingsService ratingsService;
	
	@Autowired
    private FavoriteService favoritesService;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	@GetMapping(value= "/{customerId}",produces="application/json")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId)
	{
		return new ResponseEntity<Customer>(customerService.getCustomerById(customerId),HttpStatus.OK);
	}
	@PutMapping(value="/{customerId}",consumes="application/json")
    public HttpStatus updateCustomer(@RequestBody Customer customerDetails) {
        return customerService.updateCustomer(customerDetails) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
    }
	@DeleteMapping(value="/{customerId}")
	public HttpStatus deleteCustomerById(@PathVariable int customerId)
	{
		return customerService.deleteCustomerById(customerId) ? HttpStatus.OK : HttpStatus.NOT_FOUND ;
	}
	@GetMapping(value="/{customerId}/orders",produces="application/json")
    public List<Orders> getOrdersByCustomerId(@PathVariable int customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }
	@GetMapping(value="/{customerId}/reviews",produces="application/json")
    public List<String> getReviewsByCustomerId(@PathVariable int customerId) {
        return ratingsService.getReviewsByCustomerId(customerId);
    }
	@PostMapping(consumes="application/json")
    public HttpStatus addCustomer(@RequestBody Customer customerDetails) {
		System.out.println(customerDetails.getCustomerId());
        return customerService.addCustomer(customerDetails) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED;
    }
	@DeleteMapping("/{customerId}/favorites/{restaurantId}")
    public String removeFavoriteRestaurant(@PathVariable int customerId, @PathVariable int restaurantId) {
        favoritesService.removeFavoriteRestaurant(customerId, restaurantId);
        return "Customer's favorite restaurant removed successfully.";
    }
}
