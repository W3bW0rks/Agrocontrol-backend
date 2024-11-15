package com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentProductRepository extends JpaRepository<PaymentProduct, Long> {
    List<PaymentProduct> findByOwnerProductId(Long ownerProductId);
    boolean existsByOwnerProductId(Long ownerProductId);
}
