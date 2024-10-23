package com.agrocontrol.backend.iam.domain.model.commands;

public record SignUpDistributorCommand(String fullName, String email, String password,
                                       String city, String country, String phone, String companyName, String ruc) {
}
