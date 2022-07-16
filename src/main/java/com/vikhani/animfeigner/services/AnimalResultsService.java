package com.vikhani.animfeigner.services;

import com.vikhani.animfeigner.client.AnimalRequestsClient;
import com.vikhani.animfeigner.dtos.AnimalDto;
import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.repositories.AnimalsResultsRepository;

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

        ResponseEntity<List<AnimalDto>> requestRes = client.getAnimals();
        result.setHttpStatus(requestRes.getStatusCode().value());

        if (result.getHttpStatus() == 200) {
            List<AnimalDto> res = requestRes.getBody();
            if (res != null && !res.isEmpty()) {
                List<String> names = res.stream()
                        .map(AnimalDto::getNickname)
                        .collect(Collectors.toCollection(ArrayList::new));

                result.setAnimalNames(String.join(",", names));
            }
        }

        return addAnimalResult(result);
    }
}
