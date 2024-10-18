package com.agrocontrol.backend.iam.interfaces.rest.resource;

import java.util.List;

public record AuthenticatedUserResource(Long id, String username, String token, List<String> roles) {

}
