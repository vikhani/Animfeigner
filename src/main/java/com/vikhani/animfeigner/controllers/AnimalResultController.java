package com.vikhani.animfeigner.controllers;

import com.vikhani.animfeigner.client.AnimalRequestsClient;
import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.services.AnimalResultsService;

import feign.FeignException;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/animals")
@AllArgsConstructor
public class AnimalResultController {
    private final AnimalRequestsClient client;

    private AnimalResultsService service;

    @GetMapping
    public ResponseEntity getAnimals() {
        try {
            // TODO move business logic
            AnimalResult result = new AnimalResult();
            result.setUUId(UUID.randomUUID());
            ResponseEntity requestRes = client.getAnimals();
            result.setHttpStatus(requestRes.getStatusCode().value());

            if (result.getHttpStatus() == 200) {
                List responseBody = (ArrayList) requestRes.getBody();
                if(responseBody != null)
                {
                    List<String> names = (List<String>) responseBody.stream().map(x -> ((HashMap) x).get("nickname"))
                            .collect(Collectors.toList());
                    result.setAnimalNames(String.join(",", names));
                }
            }

            AnimalResult savedRes = service.addAnimalResult(result);

            return new ResponseEntity(savedRes, HttpStatus.OK);

        } catch (FeignException ex) {
            return new ResponseEntity(ex.responseBody(), HttpStatus.valueOf(ex.status()));
        }
    }

}
