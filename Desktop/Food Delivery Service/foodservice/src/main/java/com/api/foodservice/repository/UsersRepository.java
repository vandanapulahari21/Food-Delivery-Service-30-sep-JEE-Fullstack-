package com.api.foodservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import com.api.foodservice.entity.Users;
 
 
public interface UsersRepository extends JpaRepository<Users, String> {
	@Query(nativeQuery = true,value="select role from users where userid=?1 and password=?2")
	String findByUserIdAndPassword(String userId,String password);

}