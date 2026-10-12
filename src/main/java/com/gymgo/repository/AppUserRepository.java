


package com.gymgo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gymgo.model.AppUser;

public interface AppUserRepository
        extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
