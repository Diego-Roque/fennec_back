package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.PaginatedResult;

public interface PropertyRepository {
    PaginatedResult<Property> getAllProperties(int page, int limit);
}
