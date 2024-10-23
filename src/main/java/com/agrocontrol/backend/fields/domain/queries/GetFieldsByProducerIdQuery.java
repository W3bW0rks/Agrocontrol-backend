package com.agrocontrol.backend.fields.domain.queries;

import com.agrocontrol.backend.fields.domain.valueobjects.ProducerId;

/**
 *  Query to get field by a producer ID
 * @param producerId
 */
public record GetFieldsByProducerIdQuery(ProducerId producerId) {
}
