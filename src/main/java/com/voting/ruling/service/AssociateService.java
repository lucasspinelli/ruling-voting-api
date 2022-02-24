package com.voting.ruling.service;

import com.voting.ruling.model.Associate;
import com.voting.ruling.repository.AssociateRepository;
import com.voting.ruling.associate.controller.CpfVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateService {
    @Autowired
    private AssociateRepository associateRepository;

    public List<Associate> associateList(){
        return associateRepository.findAll();
    }

    public void save(Associate associate) {
        associateRepository.save(associate);
    }

    public Associate getById(Long id) {
        return associateRepository.findById(id).get();
    }

    public boolean ableToVote(String cpf){
        CpfVerification cpfVerification = new CpfVerification();
        return cpfVerification.isAbleToVote(cpf);
    }
}
