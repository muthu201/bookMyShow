package com.bookmyshow.bookMyShow.Service;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.UserDao;
import com.bookmyshow.bookMyShow.Dto.UserDto;
import com.bookmyshow.bookMyShow.Entity.User;
import com.bookmyshow.bookMyShow.Exception.UserNotFound;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao uDao;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user){
		UserDto uDto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(uDao.saveUser(user), uDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId){
		UserDto uDto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		User user=uDao.findUser(userId);
		if(user != null) {
		mapper.map(user, uDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("user not found for the given id");
	}
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userId){
		UserDto uDto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		User user=uDao.deleteUser(userId);
		if(user != null) {
		mapper.map(user, uDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.OK);
		}
		throw new UserNotFound("user not deleted because,user not found for the given id");
	}
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User user,int userId){
		UserDto uDto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		User newUser=uDao.findUser(userId);
		if(newUser != null) {
		mapper.map(uDao.updateUser(user,userId), uDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.OK);
		}
		throw new UserNotFound("user not updated because,user not found for the given id");
	}
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		List<UserDto> uDto=new ArrayList<UserDto>();
		ModelMapper mapper=new ModelMapper();
		List<User> userList=uDao.findAllUser();
		mapper.map(userList, uDto);
		ResponseStructure<List<UserDto>> structure=new ResponseStructure<List<UserDto>>();
		structure.setMessage("find all User success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(uDto);;
		return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure,HttpStatus.FOUND);
	}
}
