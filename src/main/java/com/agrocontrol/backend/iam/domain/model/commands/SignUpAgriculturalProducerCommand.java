package com.agrocontrol.backend.iam.domain.model.commands;

public record SignUpAgriculturalProducerCommand(String fullName, String username, String password,
                                                String city, String country, String phone, String dni) {
}
