package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.UserRepresentationDto;
import com.blueTeam.medicalService.entity.User;
import com.blueTeam.medicalService.mapper.util.PasswordEncoderUtil;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(
        componentModel = SPRING
)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoderUtil passwordEncoder;

    public abstract UserRepresentationDto mapToUserDto(User user);

    public abstract User mapToUser(UserRepresentationDto dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract User mapToUser(UserCreateEditDto dto);
}
