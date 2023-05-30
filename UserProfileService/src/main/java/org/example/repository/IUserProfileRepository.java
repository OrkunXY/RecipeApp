package org.example.repository;

import org.example.repository.entity.UserProfileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface IUserProfileRepository extends MongoRepository<UserProfileEntity, String> {


    Optional<UserProfileEntity> findOptionalByAuthId(Long aLong);
}
