package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Continent;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

}
