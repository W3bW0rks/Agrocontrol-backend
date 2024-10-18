package com.agrocontrol.backend.iam.interfaces.rest.resources;

public record SignUpAgriculturalProducerResource(
        String username,
        String password,
        String fullName,
        String city,
        String country,
        String phone,
        String dni
) {
}
