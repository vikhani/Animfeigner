package com.vikhani.animfeigner;

import com.vikhani.animfeigner.services.AnimalResultsService;
import com.vikhani.animfeigner.controllers.AnimalResultController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class AnimfeignerApplicationTests {

	@Autowired
	private AnimalResultController controller;

	@Autowired
	private AnimalResultsService service;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
		Assertions.assertNotNull(service);
	}

}
