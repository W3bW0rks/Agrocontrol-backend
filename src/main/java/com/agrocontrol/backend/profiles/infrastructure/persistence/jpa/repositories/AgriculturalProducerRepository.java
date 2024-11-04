package com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories;


import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgriculturalProducerRepository extends JpaRepository<AgriculturalProducer, Long> {
    Optional<AgriculturalProducer> findAgriculturalProducerByUserId(Long userId);
    // Verificar existencia de DNI
    boolean existsByDni_Dni(String dni);

    // Verificar existencia de Phone
    boolean existsByPhone_Phone(String phone);

    boolean existsByUserId(Long userId);
}
