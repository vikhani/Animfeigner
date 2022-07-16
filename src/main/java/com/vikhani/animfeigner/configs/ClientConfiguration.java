package com.vikhani.animfeigner.configs;

import feign.auth.BasicAuthRequestInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        // TODO move credentials to environment variables
        return new BasicAuthRequestInterceptor("user", "password");
    }
}
