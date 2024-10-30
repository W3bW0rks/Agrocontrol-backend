package com.agrocontrol.backend.fields.domain.model.commands;

public record CreateWorkerCommand(
        Long producerId,
        String fullName,
        String documentNumber
) {
    public CreateWorkerCommand {
        if(producerId == null || producerId <= 0 ) throw new IllegalArgumentException("producerId must be greater than 0");

        if(fullName == null || fullName.isBlank() ) throw new IllegalArgumentException("fullName cannot be null or empty");

        if(documentNumber == null || documentNumber.isBlank() ) throw new IllegalArgumentException("documentNumber cannot be null or empty");

    }
}
