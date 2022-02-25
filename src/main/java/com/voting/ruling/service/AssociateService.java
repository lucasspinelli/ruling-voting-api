package com.voting.ruling.service;

import com.voting.ruling.associate.controller.CpfVerification;
import com.voting.ruling.model.Associate;
import com.voting.ruling.repository.AssociateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssociateService.class);

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
        LOGGER.debug("Starting cpf validation");
        CpfVerification cpfVerification = new CpfVerification();
        return cpfVerification.isAbleToVote(cpf);
    }

    public Associate findAssociateByCpf(String cpf) {
        return associateRepository.findAssociateByCpf(cpf);
    }
}
