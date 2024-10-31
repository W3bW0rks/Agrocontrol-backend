package com.agrocontrol.backend.profiles.domain.model.commands;

public record CreateDistributorCommand(String fullName, String city, String country,
                                       String phone, String companyName, String ruc) {
}
