package com.vikhani.animfeigner.configs;

import feign.auth.BasicAuthRequestInterceptor;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class ClientConfiguration {
    @Value("${animventory.username:user}")
    private String username;

    @Value("${animventory.password:password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }
}
