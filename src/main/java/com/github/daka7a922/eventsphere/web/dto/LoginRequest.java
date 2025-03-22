package com.github.daka7a922.eventsphere.web.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Size(min = 6 ,  message = "Username must be at least 3 symbols")
    private String username;

    @Size(min = 6 ,  message = "Username must be at least 6 symbols")
    private String password;


}
