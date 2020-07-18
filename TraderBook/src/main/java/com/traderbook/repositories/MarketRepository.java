package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Market;

public interface MarketRepository extends JpaRepository<Market, Long> {

}
