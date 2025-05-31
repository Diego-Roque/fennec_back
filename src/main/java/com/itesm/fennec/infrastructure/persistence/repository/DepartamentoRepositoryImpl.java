package com.itesm.fennec.infrastructure.persistence.repository;

import java.util.List;
import java.util.Objects;

import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import com.itesm.fennec.infrastructure.persistence.entity.DepartamentoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DepartamentoRepositoryImpl implements DepartamentoRepository, PanacheRepository<DepartamentoEntity>  {
    @Override
    @Transactional
    public DepartamentoPrecioPromedioResult obtenerPromedio(String alcaldia) {
        double promedio = find("alcaldia", alcaldia)
                .stream()
                .map(DepartamentoEntity.class::cast)
                .map(DepartamentoEntity::getPrecio)
                .filter(p -> p != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return new DepartamentoPrecioPromedioResult(alcaldia, promedio);
    }

    @Override
    @Transactional
    public Long contarPorAlcaldia(String alcaldia) {
        return count("alcaldia = ?1", alcaldia);
    }

    @Override
    @Transactional
    public Double obtenerPromedioM2(String alcaldia) {
        List<DepartamentoEntity> departamentos = find("alcaldia", alcaldia).list();
        return departamentos.stream()
                .map(DepartamentoEntity::getPrecio_por_m2)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    @Transactional
    public Double obtenerPromedioTodosDepartamentos() {
        List<Double> precios = findAll()
                .stream()
                .map(DepartamentoEntity::getPrecio)
                .filter(Objects::nonNull)
                .toList();

        if (precios.isEmpty()) {
            return 0.0;
        }

        double suma = precios.stream().mapToDouble(Double::doubleValue).sum();
        return suma / precios.size();
    }

    @Override
    @Transactional
    public Double obtenerPrecioM2() {
        List<Double> precios = findAll()
                .stream()
                .map(DepartamentoEntity::getPrecio_por_m2)
                .filter(Objects::nonNull)
                .toList();

        if (precios.isEmpty()) {
            return 0.0;
        }

        double sum = precios.stream().mapToDouble(Double::doubleValue).sum();
        return sum / precios.size();
    }
}
