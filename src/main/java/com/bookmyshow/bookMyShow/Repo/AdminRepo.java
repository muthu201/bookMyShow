package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.bookMyShow.Entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	@Query("select a from Admin a where a.adminEmail=?1")
	public Admin findByEmail(String adminEmail);
}
