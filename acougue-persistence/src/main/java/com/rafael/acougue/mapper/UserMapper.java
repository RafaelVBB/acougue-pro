package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.User;
import com.rafael.acougue.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);
}
