package com.traderbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traderbook.domains.Aspect;

public interface AspectRepository extends JpaRepository<Aspect, Long> {

}
