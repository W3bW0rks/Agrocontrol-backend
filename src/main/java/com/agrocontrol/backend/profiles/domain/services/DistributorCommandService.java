package com.agrocontrol.backend.profiles.domain.services;



import com.agrocontrol.backend.profiles.domain.model.aggregates.Distributor;
import com.agrocontrol.backend.profiles.domain.model.commands.CreateDistributorCommand;

import java.util.Optional;

public interface DistributorCommandService {
    Optional<Distributor> handle(CreateDistributorCommand command, Long userId);
}
