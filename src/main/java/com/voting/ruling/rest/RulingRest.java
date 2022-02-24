package com.voting.ruling.rest;

import com.voting.ruling.model.Ruling;
import com.voting.ruling.service.RulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruling")
public class RulingRest {
    Logger logger = LoggerFactory.getLogger(RulingRest.class);

    @Autowired
    RulingService rulingService = new RulingService();


    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity rulingList(){
        return ResponseEntity.ok(rulingService.rulingList());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getRulingById(@PathVariable Long id){
        try{
            Ruling ruling = rulingService.getById(id);
            return ResponseEntity.ok(ruling);
        } catch (Exception e){
            logger.error(String.format("Cannot find ruling with id %d %s", id, e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruling not Founded " + e.getMessage());
        }
    }
}
