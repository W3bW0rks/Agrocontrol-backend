package com.agrocontrol.backend.profiles.interfaces.rest.transform;


import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.interfaces.rest.resource.AgriculturalProducerResource;

public class AgriculturalProducerResourceFromEntityAssembler {
    public static AgriculturalProducerResource toResourceFromEntity(AgriculturalProducer agriculturalProducer) {
        return new AgriculturalProducerResource(
                agriculturalProducer.getUserId(), agriculturalProducer.getId(), agriculturalProducer.getFullName(),
                agriculturalProducer.getCity(), agriculturalProducer.getCountry(), agriculturalProducer.getPhone(),
                agriculturalProducer.getDni()
        );
    }
}
