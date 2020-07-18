package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
