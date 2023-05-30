package org.example.repository;

import org.example.repository.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface IAuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findOptionalByUsername(String username);
    /*Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);

    List<Auth> findByRole(ERole role);

    Optional<Auth> findOptionalByEmail(String email);*/
}
