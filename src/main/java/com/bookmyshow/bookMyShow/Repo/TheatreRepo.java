package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookmyshow.bookMyShow.Entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>{
	
}
