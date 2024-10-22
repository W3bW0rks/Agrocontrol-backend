package com.agrocontrol.backend.iam.interfaces.rest;

import com.agrocontrol.backend.iam.domain.services.UserCommandService;
import com.agrocontrol.backend.iam.interfaces.rest.resources.*;
import com.agrocontrol.backend.iam.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * <p>
 *     This controller is responsible for handling authentication requests.
 *     It exposes two endpoints:
 *     <ul>
 *         <li>POST /api/v1/auth/sign-in</li>
 *         <li>POST /api/v1/auth/sign-up</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    /**
     * Handles the sign-in request.
     * @param signInResource the sign-in request body.
     * @return the authenticated user resource.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    /**
     * Handles the sign-up request for developers.
     * @param signUpAgriculturalProducerResource the sign-up request body.
     * @return the created user resource.
     */
    @Operation(summary = "Create Agricultural Producer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agricultural Producer created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping("/sign-up/agricultural-producer")
    public ResponseEntity<UserResource> signUpAgriculturalProducer(@RequestBody SignUpAgriculturalProducerResource signUpAgriculturalProducerResource) {
        var signUpCommand = SignUpAgriculturalProducerCommandFromResourceAssembler.toCommandFromResource(signUpAgriculturalProducerResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    /**
     * Handles the sign-up request for enterprises.
     * @param signUpDistributorResource the sign-up request body.
     * @return the created user resource.
     */
    @Operation(summary = "Create Distributor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Distributor created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping("/sign-up/distributor")
    public ResponseEntity<UserResource> signUpDistributor(@RequestBody SignUpDistributorResource signUpDistributorResource) {
        var signUpCommand = SignUpDistributorCommandFromResourceAssembler.toCommandFromResource(signUpDistributorResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
