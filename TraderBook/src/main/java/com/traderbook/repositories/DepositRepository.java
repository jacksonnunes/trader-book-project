package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
