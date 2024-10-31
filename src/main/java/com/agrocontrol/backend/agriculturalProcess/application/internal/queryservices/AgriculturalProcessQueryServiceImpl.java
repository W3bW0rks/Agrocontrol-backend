package com.agrocontrol.backend.agriculturalProcess.application.internal.queryservices;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.*;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessQueryService;
import com.agrocontrol.backend.agriculturalProcess.infrastructure.persistence.jpa.repositories.AgriculturalProcessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgriculturalProcessQueryServiceImpl implements AgriculturalProcessQueryService {
    private final AgriculturalProcessRepository agriculturalProcessRepository;

    public AgriculturalProcessQueryServiceImpl(AgriculturalProcessRepository agriculturalProcessRepository) {
        this.agriculturalProcessRepository = agriculturalProcessRepository;
    }

    @Override
    public Optional<AgriculturalProcess> handle(GetAgriculturalProcessByIdQuery query) {
        return this.agriculturalProcessRepository.findById(query.agriculturalProcessId());
    }

    @Override
    public List<AgriculturalProcess> handle(GetAgriculturalProcessByFieldIdQuery query) {
        return this.agriculturalProcessRepository.findByFieldId(query.fieldId());
    }

    @Override
    public Optional<AgriculturalProcess> handle(GetUnfinishedAgriculturalProcessByFieldIdQuery query) {
        return this.agriculturalProcessRepository.findByFinishedIsFalseAndFieldId(query.fieldId());
    }

    @Override
    public List<AgriculturalActivity> handle(GetActivitiesByActivityTypeAndAgriculturalProcessIdQuery query) {
        var agriculturalProcess = this.agriculturalProcessRepository.findById(query.agriculturalProcessId()).orElseThrow();
        return agriculturalProcess.getActivitiesByType(query.activityType());
    }

    @Override
    public Optional<AgriculturalActivity> handle(GetLastActivityByActivityTypeAndAgriculturalProcessIdQuery query) {
        var agriculturalProcess = this.agriculturalProcessRepository.findById(query.agriculturalProcessId()).orElseThrow();
        return Optional.ofNullable(agriculturalProcess.getLastActivityByType(query.activityType()));
    }
}
