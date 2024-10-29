package com.agrocontrol.backend.iam.domain.model.commands;

public record SignUpDistributorCommand(String fullName, String email, String password,
                                       String city, String country, String phone, String companyName, String ruc) {
    public SignUpDistributorCommand {
        if (!email.contains("@"))
            throw new RuntimeException("Invalid email");
        if (ruc.length() != 11)
            throw new RuntimeException("Invalid RUC");
        if (phone.length() != 9)
            throw new RuntimeException("Invalid phone number");
    }
}
