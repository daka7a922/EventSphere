package com.github.daka7a922.eventsphere.web.mapper;

import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.web.dto.UserEditRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

public static UserEditRequest mapUserToUserEditRequest(User user){

    return UserEditRequest.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .profilePicture(user.getProfilePicture())
            .build();

}
}
