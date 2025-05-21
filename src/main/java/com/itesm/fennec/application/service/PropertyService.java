package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.GetAllPropertiesUseCase;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.PaginatedResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropertyService {

    private final GetAllPropertiesUseCase getAllPropertiesUseCase;

    @Inject
    public PropertyService(GetAllPropertiesUseCase getAllPropertiesUseCase) {
        this.getAllPropertiesUseCase = getAllPropertiesUseCase;
    }

    public PaginatedResult<Property> getAllProperties(int page, int limit) {
        return getAllPropertiesUseCase.execute(page, limit);
    }
}