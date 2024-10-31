package com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.fields.domain.model.aggregates.Field;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * FieldRepository
 */
@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    /**
     * Find a list of fields by a producer ID
     * @param producerId the producer ID
     * @return a list of fields
     */
    List<Field> findByProducerId(ProducerId producerId);


    Optional<Field> findByIdAndProducerId(Long id, ProducerId producerId);
}
