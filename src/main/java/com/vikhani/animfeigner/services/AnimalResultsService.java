package com.vikhani.animfeigner.services;

import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.repositories.AnimalsResultsRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalResultsService {
    private AnimalsResultsRepository repo;

    public AnimalResult addAnimalResult(AnimalResult result){
        return repo.save(result);
    }
}
