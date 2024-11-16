package com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByUserId(Long userId);

    @Query("SELECT p.name FROM Product p WHERE p.id = :id")
    Optional<String> findNameById(Long id);

    Optional<Product> findByPreviousIdAndUserId(Long previousId, Long userId);
}
