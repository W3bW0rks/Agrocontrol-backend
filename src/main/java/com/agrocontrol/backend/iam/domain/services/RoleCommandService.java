package com.agrocontrol.backend.iam.domain.services;


import com.agrocontrol.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
