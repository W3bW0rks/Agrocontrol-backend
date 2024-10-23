package com.agrocontrol.backend.iam.infrastructure.persistence.jpa.repositories;



import com.agrocontrol.backend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);
    boolean existsByEmail(String username);
    boolean existsById(Long userId);
}
