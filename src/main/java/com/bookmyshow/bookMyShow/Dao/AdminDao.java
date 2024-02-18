package com.bookmyshow.bookMyShow.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Admin;
import com.bookmyshow.bookMyShow.Repo.AdminRepo;

@Repository
public class AdminDao{
	@Autowired
	AdminRepo aRepo;
	
	public Admin saveAdmin(Admin admin) {
		return aRepo.save(admin);
	}
	
	public Admin findAdmin(int adminId) {
		Optional<Admin> opAdmin=aRepo.findById(adminId);
		if(opAdmin.isPresent()) {
			return opAdmin.get();	
		}
		return null;
	}
	
	public Admin findByEmail(String adminEmail) {
		return aRepo.findByEmail(adminEmail);
	}
	
	public Admin deleteAdmin(int adminId) {
		Admin admin=findAdmin(adminId);
		aRepo.delete(admin);
		return admin;
	}
	
	public Admin updateAdmin(Admin admin,int adminId) {
		Admin newAdmin=findAdmin(adminId);
		if(newAdmin != null) {
			admin.setAdminId(adminId);
			return aRepo.save(admin);
		}
		return null;
	}

}
