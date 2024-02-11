package com.bookmyshow.bookMyShow.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.User;
import com.bookmyshow.bookMyShow.Repo.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	UserRepo uRepo;
	
	public User saveUser(User user) {
		return uRepo.save(user);
	}
	
	public User findUser(int userId) {
		Optional<User> opAdmin=uRepo.findById(userId);
		if(opAdmin.isPresent()) {
			return opAdmin.get();	
		}
		return null;
	}
	public User deleteUser(int userId) {
		User user=findUser(userId);
		uRepo.delete(user);
		return user;
	}
	
	public User updateUser(User user,int userId) {
		User newUser=findUser(userId);
		if(newUser != null) {
			user.setUserId(userId);
			return uRepo.save(user);
		}
		return null;
	}
	
	public List<User> findAllUser(){
		return uRepo.findAll();
	}

}
