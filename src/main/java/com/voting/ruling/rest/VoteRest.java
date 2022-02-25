package com.voting.ruling.rest;


import com.voting.ruling.service.AssociateService;
import com.voting.ruling.service.RulingService;
import com.voting.ruling.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/vote")
public class VoteRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);
    @Autowired
    RulingService rulingService;
    @Autowired
    VoteService voteService;
    @Autowired
    AssociateService associateService;

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public void rulingVote(
            @PathVariable Long id,
            String cpf
    ) {

    }
}
