package com.voting.ruling.Service;

import com.voting.ruling.Repository.RulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RulingService {
    @Autowired
    private RulingRepository rulingRepository;
}
