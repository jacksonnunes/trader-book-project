package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Sport;

public interface SportRepository extends JpaRepository<Sport, Long> {

}
