package com.bookmyshow.bookMyShow.Service;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.TheatreAdminDao;
import com.bookmyshow.bookMyShow.Dao.TheatreDao;
import com.bookmyshow.bookMyShow.Dto.TheatreAdminDto;
import com.bookmyshow.bookMyShow.Entity.Theatre;
import com.bookmyshow.bookMyShow.Entity.TheatreAdmin;
import com.bookmyshow.bookMyShow.Exception.EmailWrongException;
import com.bookmyshow.bookMyShow.Exception.PasswordWrongException;
import com.bookmyshow.bookMyShow.Exception.TheatreAdminNotFound;
import com.bookmyshow.bookMyShow.Exception.TheatreNotFound;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@Service
public class TheatreAdminService {
	@Autowired
	TheatreAdminDao taDao;
	@Autowired
	TheatreDao tDao;
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveAdmin(TheatreAdmin theatreAdmin){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(taDao.saveAdmin(theatreAdmin), taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findAdmin(int adminId){
		TheatreAdminDto aDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin admin=taDao.findAdmin(adminId);
		if(admin != null) {
		mapper.map(admin, aDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage(" Theatre Admin found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("theatre Admin not found for the given id");
	}
	 
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findByEmail(String theatreAdminEmail,String theatreAdminPassword){
		TheatreAdminDto aDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin tadmin=taDao.findByEmail(theatreAdminEmail);
		if(tadmin.getTheatreAdminEmail().equals(theatreAdminEmail)) {
			if(tadmin.getTheatreAdminPassword().equals(theatreAdminPassword)) {
				mapper.map(tadmin, aDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setData(aDto);
				structure.setMessage("theatre Admin login success");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
			}
			throw new PasswordWrongException("theatre Admin Password is wrong");
		}
		throw new EmailWrongException("theatre admin email is wrong");
	}

	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateAdmin(TheatreAdmin theatreAdmin,int theatreAdminId){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin exAdmin=taDao.findAdmin(theatreAdminId);
		if(exAdmin != null) {
		mapper.map(taDao.updateAdmin(theatreAdmin,theatreAdminId), taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatre Admin not updated because,theatre Admin not found for the given id");
	}
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteAdmin(int theatreAdminId){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin admin=taDao.deleteAdmin(theatreAdminId);
		if(admin != null) {
		mapper.map(admin, taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatre Admin not deleted because,theatre Admin not found for the given id");
	}
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(int theatreAdminId,int theatreId){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin tAdmin=taDao.findAdmin(theatreAdminId);
		Theatre theatre=tDao.findTheatre(theatreId);
		if(tAdmin != null) {
			if(theatre != null) {
				tAdmin.setAdTheatre(theatre);
				mapper.map(taDao.updateAdmin(tAdmin, theatreAdminId), taDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setMessage("assign theatre to Theatre Admin success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(taDto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			else {
				throw new TheatreNotFound("theatre not assigned to the theatre admin because,theatre not found for the given id");
			}
			
		}
		throw new TheatreAdminNotFound("we can't assign theatre to theatre admin because,theatre Admin not found for the given id");
	}

	
}
