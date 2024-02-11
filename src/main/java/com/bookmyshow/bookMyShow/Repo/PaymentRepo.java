package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bookMyShow.Entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
