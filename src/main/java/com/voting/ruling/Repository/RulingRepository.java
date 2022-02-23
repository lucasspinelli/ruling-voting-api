package com.voting.ruling.Repository;

import com.voting.ruling.Model.Ruling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingRepository extends JpaRepository<Ruling, Long> {
}
