package com.agrocontrol.backend.profiles.interfaces.rest.transform;


import com.agrocontrol.backend.profiles.domain.model.aggregates.Distributor;
import com.agrocontrol.backend.profiles.interfaces.rest.resource.DistributorResource;

public class DistributorResourceFromEntityAssembler {
    public static DistributorResource toResourceFromEntity(Distributor distributor) {
        return new DistributorResource(
                distributor.getUserId(), distributor.getId(), distributor.getFullName(), distributor.getCity(),
                distributor.getCountry(), distributor.getPhone(), distributor.getCompanyName(), distributor.getRuc()
        );
    }
}
