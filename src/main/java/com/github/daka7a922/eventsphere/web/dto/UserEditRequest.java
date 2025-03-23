package com.github.daka7a922.eventsphere.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
public class UserEditRequest {

    @Size(max = 20, message = "First name can be maximum 20 characters")
    private String firstName;

    @Size(max = 20, message = "Last name can be maximum 20 characters")
    private String lastName;

    @Email(message = "Email must be a valid email address")
    private String email;

    @URL(message = "Profile picture must be valid URL")
    private String profilePicture;


}
