package com.agrocontrol.backend.iam.application.acl;

import com.agrocontrol.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.agrocontrol.backend.iam.domain.services.UserQueryService;
import com.agrocontrol.backend.iam.interfaces.acl.UsersContextFacade;
import org.springframework.stereotype.Service;

@Service
public class UserContextFacadeImpl implements UsersContextFacade {
    private final UserQueryService userQueryService;

    public UserContextFacadeImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    // inherited javadoc
    @Override
    public boolean exitsUserById(Long id) {
        var query = new GetUserByIdQuery(id);
        var existingUser = userQueryService.handle(query);
        return existingUser.isPresent();
    }
}
