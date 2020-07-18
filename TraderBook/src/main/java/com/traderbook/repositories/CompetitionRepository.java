package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
