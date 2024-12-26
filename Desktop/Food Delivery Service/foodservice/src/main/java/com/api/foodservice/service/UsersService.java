package com.api.foodservice.service;
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
 
import org.springframework.transaction.annotation.Transactional;
 
import com.api.foodservice.repository.UsersRepository;
 
@Service
 
public class UsersService {
 
	@Autowired
 
	UsersRepository usersRepository;
 
	@Transactional(readOnly=true)
 
	public String findByUserIdAndPassword(String userId,String password)
 
	{
 
		String role= usersRepository.findByUserIdAndPassword(userId, password);
 
			return (role !=null) ? role : "Invalid User";
 
	}
 
}