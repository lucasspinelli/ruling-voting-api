package com.voting.ruling.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voting.ruling.adapters.SessionAdapter;
import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.form.VoteForm;
import com.voting.ruling.model.Session;
import com.voting.ruling.service.AssociateService;
import com.voting.ruling.service.RulingService;
import com.voting.ruling.service.SessionService;
import com.voting.ruling.service.VoteService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/session")
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

    @ApiOperation(value = "Find session by id, here we can see the vote result")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getSession(@PathVariable Long id) {
        try {
            Session session = sessionService.getById(id);
            return ResponseEntity.ok(adapter.toJson(session));
        } catch (Exception e) {
            LOGGER.error(String.format("Cannot find Session with id %d %s", id, e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Session %d not Founded %s", id, e.getMessage()));
        }
    }

    @ApiOperation(value = "Vote in a ruling with the informed id on path")
    @RequestMapping(value = "ruling/{rulingId}/vote", method = RequestMethod.POST)
    public ResponseEntity vote(@PathVariable Long rulingId,
                               @Valid @RequestBody VoteForm voteForm
    ) {
        try {
            LOGGER.info("Starting vote validation");
            return sessionService.validateVoteSession(rulingId, voteForm, associateService, rulingService, voteService);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Cannot compute vote %s", e.getMessage()));
        }
    }
}
