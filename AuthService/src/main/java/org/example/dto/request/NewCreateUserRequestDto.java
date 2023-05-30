package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateUserRequestDto {
    private Long authId;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;

}
