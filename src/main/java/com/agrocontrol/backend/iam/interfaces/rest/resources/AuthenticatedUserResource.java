package com.agrocontrol.backend.iam.interfaces.rest.resources;

import java.util.List;

public record AuthenticatedUserResource(Long id, String email, String token, List<String> roles) {

}
