package com.agrocontrol.backend.iam.interfaces.rest.resources;

public record SignUpDistributorResource(
        String username,
        String password,
        String fullName,
        String city,
        String country,
        String phone,
        String companyName,
        String ruc
) {
}