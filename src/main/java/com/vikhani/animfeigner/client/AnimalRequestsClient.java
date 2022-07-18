package com.vikhani.animfeigner.client;

import com.vikhani.animfeigner.dtos.AnimalDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(value = "animalrequests",
        url = "${animfeigner.client.url}")
public interface AnimalRequestsClient {
    @GetMapping(value = "/animals")
    ResponseEntity<List<AnimalDto>> getAnimals();
}
