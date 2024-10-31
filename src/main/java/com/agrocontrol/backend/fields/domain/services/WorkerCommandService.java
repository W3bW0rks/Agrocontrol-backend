package com.agrocontrol.backend.fields.domain.services;

import com.agrocontrol.backend.fields.domain.model.aggregates.Worker;
import com.agrocontrol.backend.fields.domain.model.commands.CreateWorkerCommand;
import com.agrocontrol.backend.fields.domain.model.commands.DeleteWorkerCommand;

import java.util.Optional;

public interface WorkerCommandService {
    Optional<Worker> handle(CreateWorkerCommand command);

    void handle(DeleteWorkerCommand command);
}
