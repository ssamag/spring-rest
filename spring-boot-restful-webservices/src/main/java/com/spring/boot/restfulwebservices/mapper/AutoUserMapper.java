package com.spring.boot.restfulwebservices.mapper;

import com.spring.boot.restfulwebservices.dto.UserDto;
import com.spring.boot.restfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
