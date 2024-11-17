package com.agrocontrol.backend.fields.domain.services;

import com.agrocontrol.backend.fields.domain.model.aggregates.Field;
import com.agrocontrol.backend.fields.domain.model.queries.GetFieldByIdQuery;
import com.agrocontrol.backend.fields.domain.model.queries.GetFieldsByProducerIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service to query fields
 */
public interface FieldQueryService {
    /**
     * get field by a field ID
     * @param query the query to get field
     * @return the field
     */
    Optional<Field> handle(GetFieldByIdQuery query);

    /**
     * get list of fields by a producer ID
     * @param query the query to get the list of fields
     * @return the list of fields
     */
    List<Field> handle(GetFieldsByProducerIdQuery query);

}
