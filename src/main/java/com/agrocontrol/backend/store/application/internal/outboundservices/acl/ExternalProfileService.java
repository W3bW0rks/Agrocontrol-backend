package com.agrocontrol.backend.store.application.internal.outboundservices.acl;

import com.agrocontrol.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service("externalProfileServiceStore")
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public void existsDistributor(Long userId) {
        boolean exists = profilesContextFacade.exitsDistributorByUserId(userId);
        if (!exists) {
            throw new IllegalArgumentException("Distributor not found with id %s".formatted(userId));
        }
    }
}
