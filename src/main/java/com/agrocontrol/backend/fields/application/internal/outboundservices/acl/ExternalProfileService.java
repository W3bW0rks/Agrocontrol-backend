package com.agrocontrol.backend.fields.application.internal.outboundservices.acl;

import com.agrocontrol.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;


@Service("externalProfileServiceFields")
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public void exitsAgriculturalProducer(Long userId) {
        boolean exists = profilesContextFacade.exitsAgriculturalProducerByUserId(userId);
        if (!exists) {
            throw new IllegalArgumentException("Agricultural Producer not found with id %s".formatted(userId));
        }
    }
}
