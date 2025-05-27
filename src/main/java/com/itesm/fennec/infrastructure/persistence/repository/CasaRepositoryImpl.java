package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.infrastructure.persistence.entity.CasaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;



@ApplicationScoped
public class CasaRepositoryImpl implements CasaRepository, PanacheRepository<CasaEntity> {

    @Override
    @Transactional
    public CasaPrecioPromedioResult obtenerPromedio(String alcaldia) {
        double promedio = find("alcaldia", alcaldia)
                .stream()
                .map(CasaEntity.class::cast)
                .map(CasaEntity::getPrecio)
                .filter(p -> p != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return new CasaPrecioPromedioResult(alcaldia, promedio);
    }
}