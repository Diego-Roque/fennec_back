package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.PropertyListService;
import com.itesm.fennec.domain.model.PropertyList;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class GetFilteredPropertyUseCase {

    @Inject
    PropertyListService propertyListService;

    public PropertyList execute(String tipoPropiedad, Map<String, Object> filtros, int pagina, int limite) {
        return propertyListService.filtrarPropiedades(tipoPropiedad, filtros, pagina, limite);
    }
}
