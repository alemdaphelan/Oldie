package com.oldie.backend.authentication.dto;

import org.mapstruct.Mapper;
import com.oldie.backend.user.entities.UserProfile;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserResponse toUserResponse(UserProfile userProfile);
}