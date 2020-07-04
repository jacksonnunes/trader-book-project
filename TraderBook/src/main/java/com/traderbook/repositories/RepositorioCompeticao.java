package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Competicao;

public interface RepositorioCompeticao extends JpaRepository<Competicao, Long> {

}
