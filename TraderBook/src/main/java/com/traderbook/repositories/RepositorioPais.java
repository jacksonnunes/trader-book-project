package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Pais;

public interface RepositorioPais extends JpaRepository<Pais, Long> {

}
