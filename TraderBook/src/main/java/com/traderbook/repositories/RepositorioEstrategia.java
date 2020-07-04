package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Estrategia;

public interface RepositorioEstrategia extends JpaRepository<Estrategia, Long> {

}
