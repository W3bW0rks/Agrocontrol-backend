package com.agrocontrol.backend.iam.interfaces.rest.transform;

import com.agrocontrol.backend.iam.domain.model.commands.SignUpDistributorCommand;
import com.agrocontrol.backend.iam.interfaces.rest.resource.SignUpDistributorResource;

public class SignUpDistributorCommandFromResourceAssembler {
    public static SignUpDistributorCommand toCommandFromResource(SignUpDistributorResource resource) {
        return new SignUpDistributorCommand(resource.fullName(), resource.username() ,resource.password(),
                resource.city(), resource.country(), resource.phone(), resource.companyName(),resource.ruc());
    }
}
