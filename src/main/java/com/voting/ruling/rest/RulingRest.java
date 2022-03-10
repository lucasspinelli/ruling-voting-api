package com.voting.ruling.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voting.ruling.adapters.RulingAdapter;
import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.form.RulingForm;
import com.voting.ruling.form.SessionForm;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.model.Session;
import com.voting.ruling.service.RulingService;
import com.voting.ruling.service.SessionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/ruling")
public class RulingRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RulingRest.class);

    @Autowired
    RulingService rulingService;
    @Autowired
    SessionService sessionService;
    Gson adapter = new GsonBuilder().registerTypeAdapter(Ruling.class, new RulingAdapter()).create();

    @ApiOperation(value = "Get one list with all rulings")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity rulingList() {
        return ResponseEntity.ok(adapter.toJson(rulingService.rulingList()));
    }

    @ApiOperation(value = "Find a ruling by id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getRulingById(
            @PathVariable Long id
    ) {
        try {
            Ruling ruling = rulingService.getById(id);
            return ResponseEntity.ok(adapter.toJson(ruling));
        } catch (Exception e) {
            LOGGER.error(String.format("Cannot find ruling with id %d %s", id, e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruling not Founded " + e.getMessage());
        }
    }

    @ApiOperation(value = "Create new ruling with or without description")
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody RulingForm rulingForm,
                                   UriComponentsBuilder uriComponentsBuilder) {
        Ruling ruling = rulingForm.toRuling();
        try {
            rulingService.save(ruling);
            URI uri = uriComponentsBuilder.path("ruling/{id}").buildAndExpand(ruling.getId()).toUri();
            return ResponseEntity.created(uri).body(ruling.getId());
        } catch (Exception e) {
            throw new BadRequestException("Cannot create ruling " + e.getMessage());
        }
    }

    @ApiOperation(value = "Starts a session with the informed id on path return id for a session to vote")
    @RequestMapping(path = "/{id}/startSession", method = RequestMethod.POST)
    public ResponseEntity createRuling(@PathVariable(name = "id") Long id,
                                       @Valid @RequestBody SessionForm sessionForm) {
        try {
            LOGGER.info("Starting session");
            Ruling ruling = rulingService.getById(id);
            if (nonNull(ruling) && isNull(ruling.getSession())) {
                Session session = new Session(sessionForm.getExpiration());
                session.setRuling(ruling);
                sessionService.save(session);
                ruling.setSession(session);
                rulingService.save(ruling);
                return ResponseEntity.status(HttpStatus.CREATED).body(session.getId());
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session already started");
        } catch (Exception e) {
            throw new BadRequestException(String.format("Cannot access the session, probably the session isn't active anymore %s", e.getMessage()));
        }
    }
}
