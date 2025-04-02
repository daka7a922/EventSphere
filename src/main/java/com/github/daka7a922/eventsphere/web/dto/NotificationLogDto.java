package com.github.daka7a922.eventsphere.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLogDto {

    private Long id;

    private String message;

    private String recipient;

    private LocalDateTime timestamp;

    private String eventReference;
}
