package com.vikhani.Animfeigner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AnimfeignerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimfeignerApplication.class, args);
	}

}
