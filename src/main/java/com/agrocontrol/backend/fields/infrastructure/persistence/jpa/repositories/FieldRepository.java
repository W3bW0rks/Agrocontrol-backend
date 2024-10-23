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
     * Find a field by a name
     * @param name the field name
     * @return an optional of the field
     */
    Optional<Field> findByName(String name);

    /**
     * Find a field by a field ID
     * @param fieldId the field ID
     * @return an optional of the field
     */
    Optional<Field> findById(Long fieldId);

    /**
     * Find a list of fields by a producer ID
     * @param producerId the producer ID
     * @return a list of fields
     */
    List<Field> findByProducerId(ProducerId producerId);

    /**
     * Check if a field exists by its field ID
     * @param fieldId the field ID
     * @return True if the field exists, false otherwise
     */
    boolean existsById(Long fieldId);

}
