package com.voting.ruling.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voting.ruling.adapters.SessionAdapter;
import com.voting.ruling.form.VoteForm;
import com.voting.ruling.model.Associate;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.model.Session;
import com.voting.ruling.model.Vote;
import com.voting.ruling.service.AssociateService;
import com.voting.ruling.service.RulingService;
import com.voting.ruling.service.SessionService;
import com.voting.ruling.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/session")
public class SessionRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssociateService.class);
    Gson adapter = new GsonBuilder().registerTypeAdapter(Session.class, new SessionAdapter()).create();

    @Autowired
    SessionService sessionService;
    @Autowired
    RulingService rulingService;
    @Autowired
    VoteService voteService;
    @Autowired
    AssociateService associateService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getSession(@PathVariable Long id){
        Session session = sessionService.getById(id);
        return ResponseEntity.ok(adapter.toJson(session));
    }

    @RequestMapping(value = "ruling/{rulingId}/vote", method = RequestMethod.POST)
    public ResponseEntity vote(@PathVariable Long rulingId,
                               @Valid @RequestBody VoteForm voteForm
    ){
        String validVote = sessionService.validateVote(voteForm.getVote());
        if(nonNull(validVote)){
            Associate associate = associateService.findAssociateByCpf(voteForm.getCpf());
            if(isNull(associate)) return ResponseEntity.badRequest().body("Cannot find associate with this cpf");
            Ruling ruling = rulingService.getById(rulingId);
            if(isNull(ruling)) return ResponseEntity.badRequest().body("No ruling found with the id: " + rulingId);
            if(isNull(ruling.getSession())) return ResponseEntity.badRequest().body("Session is not started");
            if(!(ruling.getSession().isActive())) return ResponseEntity.badRequest().body("Session is closed");
            Integer countVote = voteService.countBySessionIdAndAssociateId(ruling.getSession().getId(), associate.getId());
            if(countVote > 0){
                return ResponseEntity.badRequest().body("You already voted for this ruling");
            }
            Vote newVote = new Vote(validVote, associate, ruling.getSession());
            voteService.vote(newVote);
            return ResponseEntity.ok().body("Vote is computed");
        }
        return ResponseEntity.badRequest().body("The input vote is not valid");
    }
}
