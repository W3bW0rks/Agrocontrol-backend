package com.agrocontrol.backend.iam.interfaces.rest.transform;

import com.agrocontrol.backend.iam.domain.model.entities.Role;
import com.agrocontrol.backend.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}