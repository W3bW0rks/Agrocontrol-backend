package com.agrocontrol.backend.profiles.domain.model.commands;

public record CreateAgriculturalProducerCommand(String fullName, String city, String country,
                                                String phone, String dni) {
}
