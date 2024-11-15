package com.agrocontrol.backend.finances.interfaces.acl;

/**
 * Finances context facade.
 */
public interface FinancesContextFacade {

    /**
     * Create a new finance.
     *
     * @param agriculturalProcessId The id of the agricultural process.
     * @param type                  The type of the finance.
     * @param value                 The value of the finance.
     */
    void createFinance(Long agriculturalProcessId, String type, String description, double value);
}
