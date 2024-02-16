package com.bookmyshow.bookMyShow.Service;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.AdminDao;
import com.bookmyshow.bookMyShow.Dto.AdminDto;
import com.bookmyshow.bookMyShow.Entity.Admin;
import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Exception.AdminNotFound;
import com.bookmyshow.bookMyShow.Repo.TheatreRepo;
import com.bookmyshow.bookMyShow.util.ResponseStructure;
@Service
public class AdminService {
	@Autowired
	AdminDao aDao;
	@Autowired
	TheatreRepo tRepo;
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(aDao.saveAdmin(admin), aDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		Admin admin=aDao.findAdmin(adminId);
		if(admin != null) {
		mapper.map(admin, aDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(Admin admin,int adminId){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		Admin exAdmin=aDao.findAdmin(adminId);
		if(exAdmin != null) {
		mapper.map(aDao.updateAdmin(admin, adminId), aDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not updated because,Admin not found for the given id");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		Admin admin=aDao.deleteAdmin(adminId);
		if(admin != null) {
		mapper.map(admin, aDto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not deleted because,Admin not found for the given id");
	}
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatresToAdmin(int adminId,List<Integer> theatreIds){
		AdminDto aDto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		Admin admin=aDao.findAdmin(adminId);
		if(admin != null) {
			List<Theatre> extheatres=tRepo.findAllById(theatreIds);
			admin.setTheatresList(extheatres);
			mapper.map(aDao.updateAdmin(admin, adminId), aDto);
			ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			structure.setMessage("assign theatre to Admin success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(aDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("we can't assign theatres to Admin because,Admin not found for the given id");
	}
}
