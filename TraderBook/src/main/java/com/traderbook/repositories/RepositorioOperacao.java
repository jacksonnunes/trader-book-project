package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Operacao;

public interface RepositorioOperacao extends JpaRepository<Operacao, Long> {

}
