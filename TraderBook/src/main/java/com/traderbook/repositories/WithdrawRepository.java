package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

}
