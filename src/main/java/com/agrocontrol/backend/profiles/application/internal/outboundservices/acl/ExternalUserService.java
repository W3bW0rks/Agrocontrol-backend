package com.agrocontrol.backend.profiles.application.internal.outboundservices.acl;


import com.agrocontrol.backend.iam.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {
    private final UserContextFacade userContextFacade;

    public ExternalUserService(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public void validateUserExists(Long userId) {
        boolean exists = userContextFacade.checkIfUserExists(userId);
        if (!exists) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }
}
