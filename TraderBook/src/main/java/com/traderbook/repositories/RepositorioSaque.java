package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Saque;

public interface RepositorioSaque extends JpaRepository<Saque, Long> {

}
