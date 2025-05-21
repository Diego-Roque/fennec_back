package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.PaginatedResult;
import com.itesm.fennec.domain.repository.PropertyRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetAllPropertiesUseCase {

    private final PropertyRepository propertyRepository;

    @Inject
    public GetAllPropertiesUseCase(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PaginatedResult<Property> execute(int page, int limit) {
        return propertyRepository.getAllProperties(page, limit);
    }
}