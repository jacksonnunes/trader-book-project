package com.traderbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Operation;
import com.traderbook.domains.Users;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	List<Operation> findByUser(Users user);

}
