package com.voting.ruling.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voting.ruling.adapters.RulingAdapter;
import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.model.Ruling;
import com.voting.ruling.model.Session;
import com.voting.ruling.service.RulingService;
import com.voting.ruling.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/ruling")
public class RulingRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RulingRest.class);

    @Autowired
    RulingService rulingService;
    @Autowired
    SessionService sessionService;
    Gson adapter = new GsonBuilder().registerTypeAdapter(Ruling.class, new RulingAdapter()).create();

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity rulingList() {
        return ResponseEntity.ok(adapter.toJson(rulingService.rulingList()));
    }

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

    @RequestMapping(path = "/{id}/startSession", method = RequestMethod.POST)
    public ResponseEntity createRuling(@PathVariable(name = "id") Long id,
                                       Double expiration) {
        try {
            LOGGER.debug("Starting session");
            Ruling ruling = rulingService.getById(id);
            if (nonNull(ruling) && isNull(ruling.getSession())) {
                Session session = new Session(expiration);
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
