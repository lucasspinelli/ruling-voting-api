package com.voting.ruling.associate.controller;

import com.voting.ruling.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CpfVerification {

    public boolean isAbleToVote(String cpf) {
        try {
            RestTemplate rest = new RestTemplate();
            String urlCpfValidator = "https://user-info.herokuapp.com/users/{cpf}";
            ResponseEntity<String> response = rest.getForEntity(urlCpfValidator, String.class, cpf);

            /*
             * According to the instructions of usage for this cpf API
             * if we have a status 200 = cpf is valid
             * if we have status 404 = cpf is invalid, for to many reasons
             * so we can verify the status instead of walking through a json
             * to find the string 'ABLE_TO_VOTE'
             */

            HttpStatus statusCode = response.getStatusCode();
            return statusCode.value() == 200;

        } catch (Exception e) {
            throw new NotFoundException(String.format("Cannot validate cpf %s %s", cpf, e.getMessage()));
        }
    }
}
