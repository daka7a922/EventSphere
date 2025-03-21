package com.github.daka7a922.eventsphere.web.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {


    @Size(min = 3, message = "Username must be minimum 3 symbols")
    private String username;

    @Size(min = 6 ,  message = "Password must be at least 6 symbols")
    private String password;
}
