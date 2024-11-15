package com.agrocontrol.backend.payment.application.internal.outboundservices;

import com.agrocontrol.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service("externalProfileServicePaymentProduct")
public class ExternalProfileContextFacade {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileContextFacade(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public void exitsAgriculturalProducer(Long userId) {
        boolean exists = profilesContextFacade.exitsAgriculturalProducerByUserId(userId);
        if (!exists) {
            throw new IllegalArgumentException("Agricultural Producer not found with id %s".formatted(userId));
        }
    }
}
