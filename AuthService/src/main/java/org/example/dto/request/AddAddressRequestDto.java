package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAddressRequestDto {

    private String street;
    private String area;
    private String district;
    private String city;
    private String country;
    private String buildingNumber;
    private String apartmentNumber;
    private String zipcode;
    private String token;

}
