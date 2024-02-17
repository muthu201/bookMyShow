package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.bookMyShow.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.userEmail=?1")
	public User findByEmail(String userEmail);
	
}
