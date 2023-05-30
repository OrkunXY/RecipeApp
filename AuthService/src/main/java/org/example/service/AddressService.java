package org.example.service;

import org.example.dto.request.AddAddressRequestDto;
import org.example.exception.AuthManagerException;
import org.example.exception.ErrorType;
import org.example.mapper.IAddressMapper;
import org.example.repository.Address;
import org.example.repository.IAddressRepository;
import org.example.utility.JwtTokenProvider;
import org.example.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService extends ServiceManager<Address, Long> {
private final IAddressRepository addressRepository;
private final JwtTokenProvider jwtTokenProvider;
    public AddressService(JpaRepository<Address, Long> repository,
                          IAddressRepository addressRepository,
                          JwtTokenProvider jwtTokenProvider) {
        super(repository);
        this.addressRepository= addressRepository;
        this.jwtTokenProvider= jwtTokenProvider;
    }
    public Address createAddress (AddAddressRequestDto dto) {
        Optional<Long> userId= jwtTokenProvider.getIdFromToken(dto.getToken());
        if (userId.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }else {
            Address address = IAddressMapper.INTANCE.AddAddressDtoToAddress(dto);
            address.setAuthId(userId.get());
            return save(address);
        }
    }
}
