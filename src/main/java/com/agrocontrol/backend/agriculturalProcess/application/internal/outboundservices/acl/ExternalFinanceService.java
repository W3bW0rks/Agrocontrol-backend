package com.agrocontrol.backend.agriculturalProcess.application.internal.outboundservices.acl;

import com.agrocontrol.backend.finances.interfaces.acl.FinancesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalFinanceService {
    private final FinancesContextFacade financesContextFacade;

    public ExternalFinanceService(FinancesContextFacade financesContextFacade) {
        this.financesContextFacade = financesContextFacade;
    }

    public void createFinance(Long agriculturalProcessId, String type, String description, double value) {
        this.financesContextFacade.createFinance(agriculturalProcessId, type, description, value);
    }
}
