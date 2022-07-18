package com.vikhani.animfeigner;

import com.vikhani.animfeigner.services.AnimalRequestService;
import com.vikhani.animfeigner.controllers.AnimalRequestController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class AnimfeignerApplicationTests {

	@Autowired
	private AnimalRequestController controller;

	@Autowired
	private AnimalRequestService service;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
		Assertions.assertNotNull(service);
	}

}
