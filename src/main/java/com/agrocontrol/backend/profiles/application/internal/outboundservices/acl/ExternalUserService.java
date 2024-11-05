package com.agrocontrol.backend.profiles.application.internal.outboundservices.acl;

import com.agrocontrol.backend.iam.interfaces.acl.UsersContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {
    private final UsersContextFacade userContextFacade;

    public ExternalUserService(UsersContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public void validateUserExists(Long userId) {
        boolean exists = userContextFacade.exitsUserById(userId);
        if (!exists) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }
}
