package com.voting.ruling.service;

import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.exception.NotFoundException;
import com.voting.ruling.form.VoteForm;
import com.voting.ruling.model.Associate;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.model.Session;
import com.voting.ruling.model.Vote;
import com.voting.ruling.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class SessionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ruling.class);

    @Autowired
    private SessionRepository sessionRepository;

    public void save(Session session) {
        sessionRepository.save(session);
    }

    public List<Session> findSessionByActive(boolean active) {
        try {
            return sessionRepository.findSessionByActive(active);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Cannot find active rulings %s", e.getMessage()));
        }
    }

    public boolean verifyExpired(Session session) {
        try {
            LOGGER.info("Verifying expired session");
            return (new Date()).getTime() - session.getCreationDate().getTime() > session.getExpiration();
        } catch (Exception e) {
            throw new BadRequestException(String.format("Cannot verify expiration status for session %d %s", session.getId(), e.getMessage()));
        }
    }

    public void changeActive(Session session, boolean active) {
        if (nonNull(session)) session.setActive(active);
        save(session);
    }

    public Session getById(Long id) {
        try {
            LOGGER.info("Trying to find session with id " + id);
            return sessionRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException(String.format("Cannot find Session with id %d %s", id, e.getMessage()));
        }
    }

    public String validateVote(String vote) {
        vote = vote.toUpperCase();
        if (vote.equals("SIM") || vote.equals("S") || vote.equals("YES") || vote.equals("Y")) return "SIM";
        else if (vote.equals("NÃO") || vote.equals("N") || vote.equals("NO") || vote.equals("NAO")) return "NÃO";
        else return null;
    }

    public ResponseEntity validateVoteSession(Long rulingId, VoteForm voteForm, AssociateService associateService,
                                              RulingService rulingService, VoteService voteService) {
        String validVote = this.validateVote(voteForm.getVote());
        if (nonNull(validVote)) {
            //Validate Associate
            Associate associate = associateService.findAssociateByCpf(voteForm.getCpf());
            if (isNull(associate)) return ResponseEntity.badRequest().body("Cannot find associate with this cpf");

            //Validate ruling
            Ruling ruling = rulingService.getById(rulingId);
            if (isNull(ruling)) return ResponseEntity.badRequest().body("No ruling found with the id: " + rulingId);

            //Validate session
            if (isNull(ruling.getSession())) return ResponseEntity.badRequest().body("Session is not started");
            if (!(ruling.getSession().isActive())) return ResponseEntity.badRequest().body("Session is closed");

            //Validate vote
            Integer countVote = voteService.countBySessionIdAndAssociateId(ruling.getSession().getId(), associate.getId());
            if (countVote > 0) {
                return ResponseEntity.badRequest().body("You already voted for this ruling");
            }
            Vote newVote = new Vote(validVote, associate, ruling.getSession());
            voteService.vote(newVote);
            return ResponseEntity.ok().body("Vote is computed");
        }
        return ResponseEntity.badRequest().body("The input vote is not valid");
    }
}
