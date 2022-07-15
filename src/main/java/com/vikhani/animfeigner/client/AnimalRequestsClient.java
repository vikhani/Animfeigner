package com.vikhani.animfeigner.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "animalrequests",
        url = "${animfeigner.client.url}")
public interface AnimalRequestsClient {
    @GetMapping(value = "/animals",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getAnimals();
}
