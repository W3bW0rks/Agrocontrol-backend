package com.agrocontrol.backend.iam.application.internal.outboundservices.acl;


import com.agrocontrol.backend.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }


    public Long createAgriculturalProducer(String fullName, String city, String country,
                                           String phone, String dni, Long userId) {
        return profileContextFacade.createAgriculturalProducer(
                fullName, city, country, phone, dni, userId
        );
    }

    public Long createDistributor(String fullName, String city, String country,
                                  String phone, String companyName, String ruc, Long userId){
        return profileContextFacade.createDistributor(
                fullName, city, country, phone, companyName, ruc, userId
        );
    }
}
