package com.voting.ruling.repository;

import com.voting.ruling.model.Ruling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingRepository extends JpaRepository<Ruling, Long> {
}
