package com.agrocontrol.backend.finances.interfaces.acl;

/**
 * Finances context facade.
 */
public interface FinancesContextFacade {

    /**
     * Create a new finance.
     * @param agriculturalProcessId The id of the agricultural process.
     * @param type The type of the finance.
     * @param value The value of the finance.
     * @return The id of the created finance.
     */
    Long createFinance(Long agriculturalProcessId, String type, double value);
}
