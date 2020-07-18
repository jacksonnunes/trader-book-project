package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Users findByEmail(String email);

}
