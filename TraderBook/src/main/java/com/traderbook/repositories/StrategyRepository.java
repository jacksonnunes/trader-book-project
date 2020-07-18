package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Strategy;

public interface StrategyRepository extends JpaRepository<Strategy, Long> {

}
