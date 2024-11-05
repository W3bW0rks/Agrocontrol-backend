package com.agrocontrol.backend.profiles.interfaces.acl;

public interface ProfilesContextFacade {


    Long createAgriculturalProducer(String fullName, String city, String country,
                                    String phone, String dni, Long userId);

    Long createDistributor(String fullName, String city, String country,
                           String phone, String companyName, String ruc, Long userId);

    boolean exitsAgriculturalProducerByUserId(Long userId);

    boolean exitsDistributorByUserId(Long userId);
}
