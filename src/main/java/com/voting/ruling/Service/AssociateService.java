package com.voting.ruling.Service;

import com.voting.ruling.Model.Associate;
import com.voting.ruling.Repository.AssociateRepository;
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
}
