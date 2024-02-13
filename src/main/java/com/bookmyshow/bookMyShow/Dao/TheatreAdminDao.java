package com.bookmyshow.bookMyShow.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.TheatreAdmin;
import com.bookmyshow.bookMyShow.Repo.TheatreAdminRepo;

@Repository
public class TheatreAdminDao {
	@Autowired
	TheatreAdminRepo atRepo;
	public TheatreAdmin saveAdmin(TheatreAdmin tAdmin) {
		return atRepo.save(tAdmin);
	}
	
	public TheatreAdmin findAdmin(int theatreAdminId) {
		Optional<TheatreAdmin> opTheatreAdmin=atRepo.findById(theatreAdminId);
		if(opTheatreAdmin.isPresent()) {
			return opTheatreAdmin.get();	
		}
		return null;
	}
	
	public TheatreAdmin deleteAdmin(int theatreAdminId) {
		TheatreAdmin tAdmin=findAdmin(theatreAdminId);
		atRepo.delete(tAdmin);
		return tAdmin;
	}
	
	public TheatreAdmin updateAdmin(TheatreAdmin tAdmin,int TheatreAdminId) {
		TheatreAdmin newtAdmin=findAdmin(TheatreAdminId);
		if(newtAdmin != null) {
			tAdmin.setTheatreAdminID(TheatreAdminId);;
			return atRepo.save(tAdmin);
		}
		return null;
	}

	
}
