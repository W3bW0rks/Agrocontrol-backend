package com.agrocontrol.backend.agriculturalProcess.application.internal.outboundservices.acl;

import com.agrocontrol.backend.finances.interfaces.acl.FinancesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalFinanceService {
    private final FinancesContextFacade financesContextFacade;

    public ExternalFinanceService(FinancesContextFacade financesContextFacade) {
        this.financesContextFacade = financesContextFacade;
    }

    public Optional<Long> createFinance(Long agriculturalProcessId, String type, double value) {
        var financeId = financesContextFacade.createFinance(agriculturalProcessId, type, value);
        return financeId == 0L ? Optional.empty() : Optional.of(financeId);
    }
}
