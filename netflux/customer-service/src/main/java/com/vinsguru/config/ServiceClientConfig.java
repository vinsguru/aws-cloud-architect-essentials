package com.vinsguru.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ServiceClientConfig {

    private static final Logger log = LoggerFactory.getLogger(ServiceClientConfig.class);

    @Bean
    public RestClient restClient(@Value("${movie.service.url}") String baseUrl) {
        log.info("movie service url: {}", baseUrl);
        return RestClient.builder()
                         .baseUrl(baseUrl)
                         .build();
    }

}
