package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

public record AddIrrigationToProcessCommand(
        Integer hoursIrrigated,
        Long agriculturalProcessId
){
}
