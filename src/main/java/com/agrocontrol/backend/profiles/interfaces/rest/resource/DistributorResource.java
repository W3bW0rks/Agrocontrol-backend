package com.agrocontrol.backend.profiles.interfaces.rest.resource;

public record DistributorResource(
        Long userId,
        Long DistributorId,
        String fullName,
        String city,
        String country,
        String phone,
        String companyName,
        String ruc
) {
}
