package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmyshow.bookMyShow.Entity.TheatreAdmin;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdmin, Integer>{
	
	@Query("select t from TheatreAdmin t where t.theatreAdminEmail=?1")
	public TheatreAdmin findByEmail(String theatreAdminEmail);
}
