package com.bookmyshow.bookMyShow.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bookMyShow.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
