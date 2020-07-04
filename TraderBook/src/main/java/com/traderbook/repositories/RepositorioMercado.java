package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Mercado;

public interface RepositorioMercado extends JpaRepository<Mercado, Long> {

}
