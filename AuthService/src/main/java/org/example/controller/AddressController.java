package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.AddAddressRequestDto;
import org.example.dto.request.RegisterRequestDto;
import org.example.repository.Address;
import org.example.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseEntity<Address> addAddressRequestDtoResponseEntity(@RequestBody @Valid AddAddressRequestDto dto) {
        return ResponseEntity.ok(addressService.createAddress(dto));
    }
}
