package com.agrocontrol.backend.fields.application.internal.queryservices;

import com.agrocontrol.backend.fields.domain.model.aggregates.Field;
import com.agrocontrol.backend.fields.domain.model.queries.GetFieldByIdQuery;
import com.agrocontrol.backend.fields.domain.model.queries.GetFieldsByProducerIdQuery;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import com.agrocontrol.backend.fields.domain.services.FieldQueryService;
import com.agrocontrol.backend.fields.infrastructure.persistence.jpa.repositories.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FieldQueryServiceImpl implements FieldQueryService {
    public final FieldRepository fieldRepository;

    public FieldQueryServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Optional<Field> handle(GetFieldByIdQuery query) {
        return this.fieldRepository.findById(query.fieldId());
    }

    @Override
    public List<Field> handle(GetFieldsByProducerIdQuery query) {

        ProducerId producerId = new ProducerId(query.producerId());


        return this.fieldRepository.findByProducerId(producerId);
    }


}
