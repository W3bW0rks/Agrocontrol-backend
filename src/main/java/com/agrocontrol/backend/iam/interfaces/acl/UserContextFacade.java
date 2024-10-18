package com.agrocontrol.backend.iam.interfaces.acl;


import com.agrocontrol.backend.iam.domain.model.queries.CheckUserByIdQuery;
import com.agrocontrol.backend.iam.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

@Service
public class UserContextFacade {
    private final UserQueryService userQueryService;

    public UserContextFacade(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public boolean checkIfUserExists(Long userId) {
        var query = new CheckUserByIdQuery(userId);
        return userQueryService.handle(query);
    }
}
