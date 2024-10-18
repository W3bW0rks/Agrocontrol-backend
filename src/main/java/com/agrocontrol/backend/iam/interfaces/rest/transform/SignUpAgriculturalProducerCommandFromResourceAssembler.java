package com.agrocontrol.backend.iam.interfaces.rest.transform;

import com.agrocontrol.backend.iam.domain.model.commands.SignUpAgriculturalProducerCommand;
import com.agrocontrol.backend.iam.interfaces.rest.resource.SignUpAgriculturalProducerResource;

public class SignUpAgriculturalProducerCommandFromResourceAssembler{
    public static SignUpAgriculturalProducerCommand toCommandFromResource(SignUpAgriculturalProducerResource resource) {
        return new SignUpAgriculturalProducerCommand(resource.fullName(), resource.username() ,resource.password(),
                resource.city(), resource.country(), resource.phone(), resource.dni());
    }
}
