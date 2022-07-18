package com.vikhani.animfeigner.controllers;

import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.services.AnimalRequestService;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/animventory/animals")
@AllArgsConstructor
public class AnimalRequestController {
    private Logger logger;

    private AnimalRequestService service;

    @GetMapping
    public ResponseEntity<Object> getAnimals() {
        AnimalResult res = service.requestAnimals();

        logger.info("Added request results to the db. Result: {}", res);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
