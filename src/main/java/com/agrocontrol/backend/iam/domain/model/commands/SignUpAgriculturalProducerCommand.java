package com.agrocontrol.backend.iam.domain.model.commands;

public record SignUpAgriculturalProducerCommand(String fullName, String email, String password,
                                                String city, String country, String phone, String dni) {
    public SignUpAgriculturalProducerCommand {
        if (!email.contains("@"))
            throw new RuntimeException("Invalid email");
        if (phone.length() != 9 || !phone.matches("\\d+")) {
            throw new RuntimeException("Invalid phone number: must contain exactly 9 digits.");
        }
        if (dni.length() != 8 || !dni.matches("\\d+")) {
            throw new RuntimeException("Invalid DNI: must contain exactly 8 digits.");
        }
    }
}
