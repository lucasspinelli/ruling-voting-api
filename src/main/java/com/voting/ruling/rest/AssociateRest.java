package com.voting.ruling.rest;

import com.voting.ruling.exception.BadRequestException;
import com.voting.ruling.form.AssociateForm;
import com.voting.ruling.model.Associate;
import com.voting.ruling.service.AssociateService;
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

@RestController
@RequestMapping("/api/v1/associate")
public class AssociateRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AssociateRest.class);
    @Autowired
    private AssociateService associateService;

    @ApiOperation(value = "Get one list with all associates")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity associateList() {
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(associateService.associateList());
    }

    @ApiOperation(value = "Register one associate with cpf accepting only numbers")
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody AssociateForm associateForm,
                                   UriComponentsBuilder uriComponentsBuilder) {
        Associate associate = associateForm.toAssociate();
        try {
            associateService.save(associate);
            URI uri = uriComponentsBuilder.path("associate/{id}").buildAndExpand(associate.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            throw new BadRequestException("Cannot save associate " + e.getMessage());
        }
    }

    @ApiOperation(value = "Find associate by id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAssociateById(@PathVariable(name = "id") Long id) {
        try {
            Associate associate = associateService.getById(id);
            return ResponseEntity.ok(associate);
        } catch (Exception e) {
            LOGGER.error(String.format("Cannot find associate with id %d %s", id, e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Associate not Founded " + e.getMessage());
        }
    }
}
