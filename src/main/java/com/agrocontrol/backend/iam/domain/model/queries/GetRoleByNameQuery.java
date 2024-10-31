package com.agrocontrol.backend.iam.domain.model.queries;


import com.agrocontrol.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
