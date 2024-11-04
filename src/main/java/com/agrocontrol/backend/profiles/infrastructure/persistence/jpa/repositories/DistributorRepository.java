package com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories;


import com.agrocontrol.backend.profiles.domain.model.aggregates.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    Optional<Distributor> findDistributorByUserId(Long id);
    boolean existsByPhone_Phone(String phone);
    boolean existsByRuc_Ruc(String ruc);
    boolean existsByUserId(Long userId);

}
