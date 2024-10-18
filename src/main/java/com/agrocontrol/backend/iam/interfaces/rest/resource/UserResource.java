package com.agrocontrol.backend.iam.interfaces.rest.resource;

import java.util.List;

public record UserResource(
    Long id,
    String username,
    List<String> roles
) {
}
