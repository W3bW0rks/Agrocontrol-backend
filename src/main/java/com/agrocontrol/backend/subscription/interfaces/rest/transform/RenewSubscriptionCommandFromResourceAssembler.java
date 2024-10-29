package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.commands.RenewSubscriptionCommand;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.RenewSubscriptionResource;

public class RenewSubscriptionCommandFromResourceAssembler {
    public static RenewSubscriptionCommand toCommandFromResource(RenewSubscriptionResource resource, Long id) {
        return new RenewSubscriptionCommand(
            resource.renewalDate(),
            id
        );
    }
}
