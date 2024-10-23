package com.agrocontrol.backend.fields.domain.model.queries;

import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;

/**
 *  Query to get field by a producer ID
 * @param producerId
 */
public record GetFieldsByProducerIdQuery(ProducerId producerId) {
}
