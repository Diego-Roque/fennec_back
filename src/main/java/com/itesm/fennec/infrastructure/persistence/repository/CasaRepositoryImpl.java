package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.infrastructure.persistence.entity.CasaEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    @Override
    @Transactional
    public Long contarPorAlcaldia(String alcaldia) {
        return count("alcaldia = ?1", alcaldia);
    }

    @Override
    @Transactional
    public Double obtenerPromedioM2(String alcaldia) {
        List<CasaEntity> casas = find("alcaldia", alcaldia).list();
        return casas.stream()
                .map(CasaEntity::getPrecio_por_m2)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    @Transactional
    public Double obtenerPromedioTodasCasas() {
        List<Double> precios = findAll()
                .stream()
                .map(CasaEntity::getPrecio)
                .filter(Objects::nonNull)
                .toList();

        if (precios.isEmpty()) {
            return 0.0;
        }

        double suma = precios.stream().mapToDouble(Double::doubleValue).sum();
        return suma / precios.size();
    }


}