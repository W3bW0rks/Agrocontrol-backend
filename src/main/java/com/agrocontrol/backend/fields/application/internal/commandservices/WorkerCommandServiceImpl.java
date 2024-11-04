package com.agrocontrol.backend.fields.application.internal.commandservices;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.domain.model.commands.CreateWorkerCommand;
import com.agrocontrol.backend.fields.domain.model.commands.DeleteWorkerCommand;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import com.agrocontrol.backend.fields.domain.services.WorkerCommandService;
import com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerCommandServiceImpl implements WorkerCommandService {
    private final WorkerRepository workerRepository;

    public WorkerCommandServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<Worker> handle(CreateWorkerCommand command) {
        var worker = new Worker(command);
        var workerCreated = workerRepository.save(worker);

        return Optional.of(workerCreated);
    }

    @Override
    public void handle(DeleteWorkerCommand command) {
        var producerId = new ProducerId(command.producerId());

        var worker = this.workerRepository.findByIdAndProducerId(command.workerId(), producerId)
                .orElseThrow(()->new IllegalArgumentException("Worker not found"));

        this.workerRepository.delete(worker);
    }
}