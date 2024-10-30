package com.agrocontrol.backend.fields.application.internal.queryservices;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.domain.model.queries.GetAllWorkersByProducerId;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import com.agrocontrol.backend.fields.domain.services.WorkerQueryService;
import com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerQueryServiceImpl implements WorkerQueryService {
    public final WorkerRepository workerRepository;

    public WorkerQueryServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> handle(GetAllWorkersByProducerId query) {
        var producerId = new ProducerId(query.producerId());
        return this.workerRepository.findAllByProducerId(producerId);
    }
}
