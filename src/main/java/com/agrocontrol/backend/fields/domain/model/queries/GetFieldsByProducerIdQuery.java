package com.agrocontrol.backend.fields.domain.model.queries;


/**
 *  Query to get field by a producer ID
 * @param producerId
 */
public record GetFieldsByProducerIdQuery(Long producerId) {
}
