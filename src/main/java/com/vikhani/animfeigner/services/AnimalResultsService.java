package com.vikhani.animfeigner.services;

import com.vikhani.animfeigner.client.AnimalRequestsClient;
import com.vikhani.animfeigner.dtos.AnimalDto;
import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.repositories.AnimalsResultsRepository;

import feign.FeignException;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalResultsService {
    private AnimalsResultsRepository repo;
    private final AnimalRequestsClient client;

    public AnimalResult addAnimalResult(AnimalResult result) {
        return repo.save(result);
    }

    public AnimalResult getAnimalsResults() {
        AnimalResult result = new AnimalResult();
        result.setUUId(UUID.randomUUID());

        try {
            ResponseEntity<List<AnimalDto>> requestRes = client.getAnimals();

            if (requestRes.getStatusCode().is2xxSuccessful()) {
                result.setHttpStatus(requestRes.getStatusCodeValue());
                List<AnimalDto> res = requestRes.getBody();
                if (res != null && !res.isEmpty()) {
                    List<String> names = res.stream()
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
            setNon2xxStatusFallback(result, ex.status());
        }

        return addAnimalResult(result);
    }

    private static void setNon2xxStatusFallback(AnimalResult res, int status) {
        res.setHttpStatus(status);
        res.setAnimalNames("");
    }
}
