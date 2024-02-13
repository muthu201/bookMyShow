package com.bookmyshow.bookMyShow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Dto.TheatreAdminDto;
import com.bookmyshow.bookMyShow.Entity.TheatreAdmin;
import com.bookmyshow.bookMyShow.Service.TheatreAdminService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@RestController
public class TheatreAdminController {
	@Autowired
	TheatreAdminService taService;
	@PostMapping
	ResponseEntity<ResponseStructure<TheatreAdminDto>> saveAdmin(@RequestBody TheatreAdmin theatreAdmin){
		return taService.saveAdmin(theatreAdmin);
	}
	
	@GetMapping
	ResponseEntity<ResponseStructure<TheatreAdminDto>> findAdmin(@RequestParam int  theatreAdminId){
		return taService.findAdmin(theatreAdminId);
	}
	
	@PutMapping
	ResponseEntity<ResponseStructure<TheatreAdminDto>> updateAdmin(@RequestBody TheatreAdmin theatreAdmin,@RequestParam int theatreAdminId){
		return taService.updateAdmin(theatreAdmin, theatreAdminId);
	}
	
	@DeleteMapping
	ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteAdmin(@RequestBody int theatreAdminId){
		return taService.deleteAdmin(theatreAdminId);
	}
}
