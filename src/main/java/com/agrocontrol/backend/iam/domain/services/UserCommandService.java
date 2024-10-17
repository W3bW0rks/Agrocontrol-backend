package com.agrocontrol.backend.iam.domain.services;



import com.agrocontrol.backend.iam.domain.model.aggregates.User;
import com.agrocontrol.backend.iam.domain.model.commands.SignInCommand;
import com.agrocontrol.backend.iam.domain.model.commands.SignUpAgriculturalProducerCommand;
import com.agrocontrol.backend.iam.domain.model.commands.SignUpDistributorCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;


public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    //Optional<User> handle(SignUpCommand command);
    Optional<User> handle(SignUpAgriculturalProducerCommand command);
    Optional<User> handle(SignUpDistributorCommand command);
}
