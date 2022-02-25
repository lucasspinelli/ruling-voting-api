package com.voting.ruling.repository;

import com.voting.ruling.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Integer countBySessionIdAndAssociateId(Long sessionId, Long rulingId);
}
