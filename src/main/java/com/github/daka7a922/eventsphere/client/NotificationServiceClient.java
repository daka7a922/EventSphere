package com.github.daka7a922.eventsphere.client;


import com.github.daka7a922.eventsphere.web.dto.NotificationLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux; // For multiple items
import reactor.core.publisher.Mono; // For single item or void

import java.util.List;

@Service
public class NotificationServiceClient {

    private final WebClient webClient;

    @Autowired
    public NotificationServiceClient(WebClient notificationWebClient) {
        this.webClient = notificationWebClient;
    }


    public Mono<NotificationLogDto> sendNotification(NotificationLogDto notificationData) {
        System.out.println("Sending notification via WebClient: " + notificationData.getMessage());
        return this.webClient.post()
                .uri("")
                .bodyValue(notificationData)
                .retrieve()
                .bodyToMono(NotificationLogDto.class)
                .doOnError(error -> System.err.println("Error sending notification: " + error.getMessage()))
                .onErrorResume(error -> Mono.empty()); // Return empty if error occurs (or handle differently)

    }


    public Flux<NotificationLogDto> getAllNotifications() {
        System.out.println("Fetching all notifications via WebClient");
        return this.webClient.get()
                .uri("")
                .retrieve()
                .bodyToFlux(NotificationLogDto.class) // Using Flux for multiple items
                .doOnError(error -> System.err.println("Error fetching notifications: " + error.getMessage()))
                .onErrorResume(error -> Flux.empty());
    }


    public Mono<NotificationLogDto> getNotificationById(Long id) {
        System.out.println("Fetching notification by ID via WebClient: " + id);
        return this.webClient.get()
                .uri("/{id}", id) // Pass ID as path variable
                .retrieve()
                .bodyToMono(NotificationLogDto.class)
                .doOnError(error -> System.err.println("Error fetching notification by ID " + id + ": " + error.getMessage()))
                .onErrorResume(error -> Mono.empty()); // Handle not found or other errors
    }

}
