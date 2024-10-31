package com.agrocontrol.backend.iam.domain.model.commands;

public record SignUpAgriculturalProducerCommand(String fullName, String email, String password,
                                                String city, String country, String phone, String dni) {
    public SignUpAgriculturalProducerCommand {
        if (!email.contains("@"))
            throw new RuntimeException("Invalid email");
        if (dni.length() != 8)
            throw new RuntimeException("Invalid DNI");
        if (phone.length() != 9)
            throw new RuntimeException("Invalid phone number");
    }
}
