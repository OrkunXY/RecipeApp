package org.example.mapper;

import org.example.dto.request.AddAddressRequestDto;
import org.example.repository.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAddressMapper {
    IAddressMapper INTANCE= Mappers.getMapper(IAddressMapper.class);
    Address AddAddressDtoToAddress(final AddAddressRequestDto dto);
}
