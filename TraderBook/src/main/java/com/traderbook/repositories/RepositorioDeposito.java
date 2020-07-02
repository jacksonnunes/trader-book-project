package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Deposito;

public interface RepositorioDeposito extends JpaRepository<Deposito, Long> {

}
