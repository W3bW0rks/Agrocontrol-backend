package com.agrocontrol.backend.fields.domain.services;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.domain.model.queries.GetAllWorkersByProducerId;

import java.util.List;

public interface WorkerQueryService {
    List<Worker> handle(GetAllWorkersByProducerId query);
}
