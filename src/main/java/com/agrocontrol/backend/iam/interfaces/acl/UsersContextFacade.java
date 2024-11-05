package com.agrocontrol.backend.iam.interfaces.acl;

/**
 * UsersContextFacade
 */
public interface UsersContextFacade {

    /**
     * Check if user exists by id
      * @param id The user id
     * @return true if user exists, false otherwise
     */
    boolean exitsUserById(Long id);
}
