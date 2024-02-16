package com.bookmyshow.bookMyShow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Dto.AdminDto;
import com.bookmyshow.bookMyShow.Entity.Admin;
import com.bookmyshow.bookMyShow.Service.AdminService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@RequestMapping("admin")
@RestController
public class AdminController {
	@Autowired
	AdminService aService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin){
		return aService.saveAdmin(admin);
	}
	@PutMapping("assignTheatresToAdmin")
	ResponseEntity<ResponseStructure<AdminDto>> assignTheatresToAdmin(@RequestParam int adminId,@RequestBody List<Integer> theatreIds){
		return aService.assignTheatresToAdmin(adminId, theatreIds);
	}
	@GetMapping
	ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@RequestParam int adminId){
		return aService.findAdmin(adminId);
	}
	
	@PutMapping
	ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestBody Admin admin,@RequestParam int adminId){
		return aService.updateAdmin(admin, adminId);
	}
	
	@DeleteMapping
	ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId){
		return aService.deleteAdmin(adminId);
	}
}
