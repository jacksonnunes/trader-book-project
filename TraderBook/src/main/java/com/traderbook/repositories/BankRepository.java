package com.traderbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Bank;
import com.traderbook.domains.Users;

public interface BankRepository extends JpaRepository<Bank, Long> {
	
	List<Bank> findByUser(Users user);

}
