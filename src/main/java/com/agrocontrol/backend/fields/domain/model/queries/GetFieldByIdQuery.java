package com.agrocontrol.backend.fields.domain.model.queries;

/**
 * Query to get field by a field ID
 * @param fieldId the field ID
 */
public record GetFieldByIdQuery(Long fieldId) {
    /**
     * Constructor
     * @param fieldId the field ID
     *                cannot be null or less than or equal to zero
     */
    public GetFieldByIdQuery {
        if(fieldId == null || fieldId <=0)
            throw new IllegalArgumentException("fieldId cannot be null or less than or equal to zero");
    }
}
