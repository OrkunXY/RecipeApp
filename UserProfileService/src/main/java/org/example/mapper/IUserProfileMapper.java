package org.example.mapper;

import org.example.dto.request.NewCreateUserRequestDto;
import org.example.dto.request.UserProfileUpdateRequestDto;
import org.example.repository.entity.UserProfileEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);
    UserProfileUpdateRequestDto toUpdateUsernameEmail(final UserProfileUpdateRequestDto dto);
    UserProfileEntity fromDtoToUserProfile(final NewCreateUserRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfileEntity updateFromDtoToUserProfile(UserProfileUpdateRequestDto dto, @MappingTarget UserProfileEntity userProfile);

}
