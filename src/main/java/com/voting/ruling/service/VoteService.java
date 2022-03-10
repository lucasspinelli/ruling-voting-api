package com.voting.ruling.service;

import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.model.Vote;
import com.voting.ruling.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private VoteRepository voteRepository;

    public void vote(Vote vote) {
        try {
            LOGGER.info("Computing vote...");
            voteRepository.save(vote);
            LOGGER.info("The vote has been submitted");
        } catch (Exception e) {
            throw new BadRequestException(String.format("We can't compute the vote with id %d %s", vote.getId(), e.getMessage()));
        }
    }

    public Integer countBySessionIdAndAssociateId(Long sessionId, Long rulingId) {
        return voteRepository.countBySessionIdAndAssociateId(sessionId, rulingId);
    }
}
