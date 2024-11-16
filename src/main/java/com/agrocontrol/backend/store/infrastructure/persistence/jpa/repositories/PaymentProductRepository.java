package com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.store.domain.model.aggregates.PaymentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentProductRepository extends JpaRepository<PaymentProduct, Long> {
    List<PaymentProduct> findByOwnerProductId(Long ownerProductId);
    List<PaymentProduct> findByUserId(Long userId);
    boolean existsByOwnerProductId(Long ownerProductId);
}
