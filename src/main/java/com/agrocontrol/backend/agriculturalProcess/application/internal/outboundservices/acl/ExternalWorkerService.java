package com.agrocontrol.backend.agriculturalProcess.application.internal.outboundservices.acl;

import com.agrocontrol.backend.fields.interfaces.acl.WorkersContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalWorkerService {
    private final WorkersContextFacade workersContextFacade;

    public ExternalWorkerService(WorkersContextFacade workersContextFacade) {
        this.workersContextFacade = workersContextFacade;
    }

    public String getWorkerNameById(Long workerId) {
        return workersContextFacade.getWorkerNameById(workerId);
    }
}
