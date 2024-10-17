package com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories;


import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgriculturalProducerRepository extends JpaRepository<AgriculturalProducer, Long> {
    Optional<AgriculturalProducer> findAgriculturalProducerByUserId(Long userId);
}
