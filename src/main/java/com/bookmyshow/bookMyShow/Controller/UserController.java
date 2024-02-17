package com.bookmyshow.bookMyShow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Dto.UserDto;
import com.bookmyshow.bookMyShow.Entity.User;
import com.bookmyshow.bookMyShow.Service.UserService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
	UserService uService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<UserDto>> saveUser(@Valid @RequestBody User user,BindingResult result){
		return uService.saveUser(user);
	}
	@GetMapping
	ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam int userId){
		return uService.findUser(userId);
	}
	@GetMapping("userLogin")
	ResponseEntity<ResponseStructure<UserDto>> findByEmail(@RequestParam String userEmail,@RequestParam String userPassword){
		return uService.findByEmail(userEmail, userPassword);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<UserDto>> saveAdmin(@RequestBody User user,@RequestParam int userId){
		return uService.updateUser(user, userId);
	}
	@DeleteMapping
	ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam int userId){
		return uService.deleteUser(userId);
	}
	@GetMapping("findAllUser")
	ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		return uService.findAllUser();
	}
	
}
