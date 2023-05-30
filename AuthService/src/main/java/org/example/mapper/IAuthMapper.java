package org.example.mapper;


import org.example.dto.request.NewCreateUserRequestDto;
import org.example.dto.request.RegisterRequestDto;
import org.example.dto.request.UpdateEmailOrUsernameRequestDto;
import org.example.dto.response.RegisterResponseDto;
import org.example.rabbitmq.model.RegisterMailModel;
import org.example.rabbitmq.model.RegisterModel;
import org.example.repository.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth fromRequestDtoToAuth(final RegisterRequestDto dto);


    @Mapping(source = "id", target = "authId")
    RegisterModel fromAuthToRegisterModel(final Auth auth);
    RegisterResponseDto fromAuthToResponseDto(final Auth auth);

    @Mapping(source = "id", target = "authId")
    NewCreateUserRequestDto fromAuthToNewCreateUserDto(final Auth auth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUsernameOrEmail(UpdateEmailOrUsernameRequestDto dto, @MappingTarget Auth auth);
}
