package com.voting.ruling.service;

import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.exception.NotFoundException;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.model.Session;
import com.voting.ruling.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class SessionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ruling.class);

    @Autowired
    private SessionRepository sessionRepository;

    public void save(Session session){
        sessionRepository.save(session);
    }

    public List<Session> findSessionByActive(boolean active) {
        try{
            return sessionRepository.findSessionByActive(active);
        }catch (Exception e){
            throw new BadRequestException(String.format("Cannot find active rulings %s", e.getMessage()));
        }
    }

    public boolean verifyExpired(Session session) {
        try{
            LOGGER.debug("Verifying expired session");
            return (new Date()).getTime() - session.getCreationDate().getTime() > session.getExpiration();
        }catch (Exception e){
            throw new BadRequestException(String.format("Cannot verify expiration status for session %d %s", session.getId(), e.getMessage()));
        }
    }

    public void changeActive(Session session, boolean active) {
        session.setActive(active);
        save(session);
    }

    public Session getById(Long id) {
        try{
            LOGGER.debug("Trying to find session with id " + id);
            return sessionRepository.findById(id).get();
        }catch (Exception e){
            throw new NotFoundException(String.format("Cannot find Session with id %d %s", id, e.getMessage()));
        }
    }

    public String validateVote(String vote){
        vote = vote.toUpperCase();
        if(vote.equals("SIM") || vote.equals("S") || vote.equals("YES") || vote.equals("Y")) return "SIM";
        else if(vote.equals("NÃO") || vote.equals("N") || vote.equals("NO") || vote.equals("NAO") ) return "NÃO";
        else return null;
    }

}
