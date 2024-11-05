package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.interfaces.rest.resources.WorkerResource;

public class WorkerResourceFromEntityAssembler {
    public static WorkerResource toResourceFromEntity(Worker entity) {
        return new WorkerResource(
                entity.getId(),
                entity.getProducerId(),
                entity.getFullName(),
                entity.getDocumentNumber()
        );
    }
}
