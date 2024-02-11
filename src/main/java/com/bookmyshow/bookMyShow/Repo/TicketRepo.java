package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bookMyShow.Entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{

}
