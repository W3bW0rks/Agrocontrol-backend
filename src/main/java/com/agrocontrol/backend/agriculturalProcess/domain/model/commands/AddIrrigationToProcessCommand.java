package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

public record AddIrrigationToProcessCommand(
        String date,
        Integer hoursIrrigated,
        Long agriculturalProcessId
){
}
