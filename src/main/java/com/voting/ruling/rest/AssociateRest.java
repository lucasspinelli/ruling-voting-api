package com.voting.ruling.rest;

import com.voting.ruling.form.AssociateForm;
import com.voting.ruling.model.Associate;
import com.voting.ruling.service.AssociateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/associate")
public class AssociateRest {
    Logger logger = LoggerFactory.getLogger(AssociateRest.class);
    @Autowired
    private AssociateService associateService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity associateList(){
       return ResponseEntity.ok(associateService.associateList());
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody AssociateForm associateForm,
                                   UriComponentsBuilder uriComponentsBuilder){
        Associate associate = associateForm.toAssociate();
        try{
            associateService.save(associate);
            URI uri = uriComponentsBuilder.path("associate/{id}").buildAndExpand(associate.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            logger.error("Cannot save associate", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAssociateById(@PathVariable(name = "id") Long id){
        try{
            Associate associate = associateService.getById(id);
            return ResponseEntity.ok(associate);
        } catch (Exception e){
            logger.error(String.format("Cannot find associate with id %d %s", id, e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associate not Founded " + e.getMessage());
        }
    }
}
