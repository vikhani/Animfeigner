package com.vikhani.animfeigner.services;

import com.vikhani.animfeigner.dtos.AnimalDto;
import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.client.AnimalRequestsClient;
import com.vikhani.animfeigner.repositories.AnimalsResultsRepository;

import feign.FeignException;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalRequestService {
    private AnimalsResultsRepository repo;

    private final AnimalRequestsClient client;

    private Logger logger;

    public AnimalResult requestAnimals() {
        AnimalResult result = new AnimalResult();
        result.setUUId(UUID.randomUUID());

        try {
            ResponseEntity<List<AnimalDto>> requestRes = getAnimalsFromClient();

            List<AnimalDto> body = requestRes.getBody();
            HttpStatus status = requestRes.getStatusCode();

            logger.info("Request response entity: {}, {}",
                    status,
                    body == null ? Collections.emptyList() : body);

            if (status.is2xxSuccessful()) {
                result.setHttpStatus(requestRes.getStatusCodeValue());
                if (body != null && !body.isEmpty()) {
                    List<String> names = body.stream()
                            .map(AnimalDto::getNickname)
                            .collect(Collectors.toCollection(ArrayList::new));

                    result.setAnimalNames(String.join(",", names));
                } else {
                    result.setAnimalNames("");
                }
            } else {
                setNon2xxStatusFallback(result, requestRes.getStatusCodeValue());
            }
        } catch (FeignException ex) {
            logger.info("Couldn't get data from Animventory: {}, {}",
                    ex.status(),
                    ex.getMessage());

            setNon2xxStatusFallback(result, ex.status());
        }

        return addAnimalResult(result);
    }

    public AnimalResult addAnimalResult(AnimalResult result) {
        return repo.save(result);
    }

    public ResponseEntity<List<AnimalDto>> getAnimalsFromClient() {
        return client.getAnimals();
    }

    private static void setNon2xxStatusFallback(AnimalResult res, int status) {
        res.setHttpStatus(status);
        res.setAnimalNames("");
    }
}
