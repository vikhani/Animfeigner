package com.vikhani.animfeigner.controllers;

import com.vikhani.animfeigner.services.AnimalResultsService;

import feign.FeignException;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/animals")
@AllArgsConstructor
public class AnimalResultController {

    private AnimalResultsService service;

    @GetMapping
    public ResponseEntity<Object> getAnimals() {
        try {
            return new ResponseEntity<>(service.getAnimalsResults(), HttpStatus.OK);
        } catch (FeignException ex) {
            return new ResponseEntity<>(ex.responseBody(), HttpStatus.valueOf(ex.status()));
        }
    }

}