package com.agrocontrol.backend.fields.domain.model.commands;

public record DeleteWorkerCommand(
        Long workerId,
        Long producerId
) {
    public DeleteWorkerCommand {
        if(workerId == null || workerId<=0){
            throw new IllegalArgumentException("Worker ID must be greater than 0");
        }
        if(producerId == null || producerId<=0){
            throw new IllegalArgumentException("Producer ID must be greater than 0");
        }
    }
}
