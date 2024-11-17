package com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findAllByProducerId(ProducerId producerId);
    Optional<Worker> findByIdAndProducerId(Long workerId, ProducerId producerId);
}
