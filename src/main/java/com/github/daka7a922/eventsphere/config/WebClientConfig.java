package com.github.daka7a922.eventsphere.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${notification.service.baseurl}") // Example property name
    private String notificationServiceBaseUrl;

    @Bean
    public WebClient notificationWebClient() {
        return WebClient.builder()
                .baseUrl(notificationServiceBaseUrl) // Base URL of the NotificationService
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
