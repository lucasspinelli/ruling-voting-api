package com.voting.ruling.service;

import com.voting.ruling.model.Ruling;
import com.voting.ruling.repository.RulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulingService {
    @Autowired
    private RulingRepository rulingRepository;

    public void save(Ruling ruling){
        rulingRepository.save(ruling);
    }

    public List<Ruling> rulingList(){
        return rulingRepository.findAll();
    }

    public Ruling getById(Long id) {
        return rulingRepository.findById(id).get();
    }
}
