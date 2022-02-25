package com.voting.ruling.service;

import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.exception.NotFoundException;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.repository.RulingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ruling.class);

    @Autowired
    private RulingRepository rulingRepository;

    public void save(Ruling ruling) {
        try {
            rulingRepository.save(ruling);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Cannot sava ruling %s", e.getMessage()));
        }

    }

    public List<Ruling> rulingList() {
        return rulingRepository.findAll();
    }

    public Ruling getById(Long id) {
        try {
            LOGGER.debug("Trying to find ruling with id " + id);
            return rulingRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException(String.format("Cannot find ruling with id %d %s", id, e.getMessage()));
        }

    }
}
