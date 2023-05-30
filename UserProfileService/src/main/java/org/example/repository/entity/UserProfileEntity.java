package org.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.example.repository.enums.EStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class UserProfileEntity extends Base implements Serializable {

    @Id
    private String id;
    private Long authId;
    private String name;
    private String surname;
    private String email;
    //unique olmali mi?
    private String username;
    private String city;
    private String district;
    private String zone;
    private String street;
    private String buildingNumber;
    private String avatar;
    @Builder.Default //bir property' ye başlangıç değeri atandığında kullanılır, new'lendiğinde kullanılmaz
    private EStatus status = EStatus.PENDING;
}
