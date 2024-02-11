package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bookMyShow.Entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer>{

}
