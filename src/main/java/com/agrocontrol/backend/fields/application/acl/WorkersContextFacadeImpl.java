package com.agrocontrol.backend.fields.application.acl;

import com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories.WorkerRepository;
import com.agrocontrol.backend.fields.interfaces.acl.WorkersContextFacade;
import org.springframework.stereotype.Service;

@Service
public class WorkersContextFacadeImpl implements WorkersContextFacade {
    private final WorkerRepository workerRepository;

    public WorkersContextFacadeImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public String getWorkerNameById(Long workerId) {
        var worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));
        return worker.getFullName();
    }
}
