package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Resource {

    private Long resourceId;
    private String description;
    private String name;
    private Integer cost;
    private Integer quantity;

    protected Resource() {}

    public Resource(Long resourceId, String description, String name, Integer cost, Integer quantity) {
        this.resourceId = resourceId;
        this.description = description;
        this.name = name;
        this.cost = cost != null ? cost : 0;
        this.quantity = quantity != null ? quantity : 0;
    }

    // Getters and setters (if needed)
}