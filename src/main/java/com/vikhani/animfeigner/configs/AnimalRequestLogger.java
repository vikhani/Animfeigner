package com.vikhani.animfeigner.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalRequestLogger {
    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger("animal_request");
    }
}
