package com.api.foodservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.api.foodservice.entity.Users;
import com.api.foodservice.service.UsersService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/users")
@RestController
public class UsersController {
	@Autowired
	UsersService usersSerivce;
	@PostMapping(consumes="application/json")
	public ResponseEntity<String> aunthenticate(@RequestBody Users user)
	{
		String response = usersSerivce.findByUserIdAndPassword(user.getUserId(),user.getPassword());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
 
}
