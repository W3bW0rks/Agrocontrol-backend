package com.agrocontrol.backend.agriculturalProcess.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgriculturalProcessRepository extends JpaRepository<AgriculturalProcess, Long> {
    List<AgriculturalProcess> findByFieldId(Long fieldId);
    Optional<AgriculturalProcess> findByFinishedIsFalseAndFieldId(Long fieldId);
}
