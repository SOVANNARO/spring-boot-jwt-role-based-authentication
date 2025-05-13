package org.tutorials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tutorials.dto.RegisterRequest;
import org.tutorials.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    User toUser(RegisterRequest registerRequest);
}