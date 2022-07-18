package com.vikhani.animfeigner.services;

import java.util.List;
import java.util.Date;
import java.util.Arrays;

import com.vikhani.animfeigner.dtos.AnimalDto;
import com.vikhani.animfeigner.models.AnimalResult;
import com.vikhani.animfeigner.client.AnimalRequestsClient;
import com.vikhani.animfeigner.repositories.AnimalsResultsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;

import feign.FeignException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalResultsServiceTest {
    @Mock
    private AnimalRequestsClient mockClient;

    @Mock
    private AnimalsResultsRepository mockRepo;

    @Mock
    private Logger mockLogger;

    @Autowired
    private AnimalResultsService service;

    @BeforeEach
    void setUp() {
        service = new AnimalResultsService(mockRepo, mockClient, mockLogger);
    }

    @Test
    void testAddAnimalResult() {
        service.addAnimalResult(new AnimalResult());
        verify(mockRepo).save(any());
    }

    @Test
    void getAnimalsFromClient() {
        service.getAnimalsFromClient();
        verify(mockClient).getAnimals();
    }

    @Test
    void getAnimalsResultsNon2xxResponse() {
        when(service.getAnimalsFromClient()).thenThrow(FeignException.InternalServerError.class);

        service.getAnimalsResults();
        verify(mockLogger).info(any(String.class), any(Integer.class), any());

        AnimalResult result = captureSavedAnimalResult();

        Assertions.assertNotNull(result);
        Assertions.assertEquals("", result.getAnimalNames());
    }

    @Test
    void getAnimalsResultsRetryableException() {
        when(service.getAnimalsFromClient()).thenThrow(feign.RetryableException.class);
        service.getAnimalsResults();
        verify(mockLogger).info(any(String.class), any(Integer.class), any());

        AnimalResult result = captureSavedAnimalResult();

        Assertions.assertNotNull(result);
        Assertions.assertEquals("", result.getAnimalNames());
    }

    @Test
    void getAnimalsResults() {
        AnimalDto firstAnimal = new AnimalDto();
        firstAnimal.setNickname("TestFirst");
        firstAnimal.setBirthday(new Date());
        firstAnimal.setGender("");
        firstAnimal.setSpecies("");

        AnimalDto secondAnimal = new AnimalDto();
        secondAnimal.setNickname("TestSecond");
        secondAnimal.setBirthday(new Date());
        secondAnimal.setGender("");
        secondAnimal.setSpecies("");

        when(service.getAnimalsFromClient()).thenReturn(ResponseEntity.ok(Arrays.asList(firstAnimal, secondAnimal)));
        service.getAnimalsResults();

        verify(mockLogger).info(any(String.class), any(HttpStatus.class), any(List.class));

        AnimalResult result = captureSavedAnimalResult();

        String expectedNamedString = String.format("%s,%s",
                firstAnimal.getNickname(),
                secondAnimal.getNickname());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedNamedString, result.getAnimalNames());
        Assertions.assertEquals(200, result.getHttpStatus());
    }

    private AnimalResult captureSavedAnimalResult() {
        ArgumentCaptor<AnimalResult> animalResultCaptor = ArgumentCaptor.forClass(AnimalResult.class);
        verify(mockRepo).save(animalResultCaptor.capture());

        return animalResultCaptor.getValue();
    }
}