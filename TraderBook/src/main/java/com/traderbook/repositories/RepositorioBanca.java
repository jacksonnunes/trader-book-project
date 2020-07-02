package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Banca;

public interface RepositorioBanca extends JpaRepository<Banca, Long> {

}
