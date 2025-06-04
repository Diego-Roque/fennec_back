package com.itesm.fennec.infrastructure.persistence.repository;

import java.util.*;
import java.util.stream.Collectors;

import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import com.itesm.fennec.infrastructure.persistence.entity.DepartamentoEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.DepartamentoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DepartamentoRepositoryImpl implements DepartamentoRepository, PanacheRepository<DepartamentoEntity>  {



    @Override
    public List<Departamento> findWithFilters(Map<String, Object> filtros, int pagina, int limite) {

        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder("1=1");

        if (filtros.containsKey("precioMin")) {
            query.append(" AND precio >= :precioMin");
            params.put("precioMin", filtros.get("precioMin"));
        }
        if (filtros.containsKey("precioMax")) {
            query.append(" AND precio <= :precioMax");
            params.put("precioMax", filtros.get("precioMax"));
        }
        if (filtros.containsKey("dimensionesMin")) {
            query.append(" AND dimensionesM2 >= :dimensionesMin");
            params.put("dimensionesMin", filtros.get("dimensionesMin"));
        }
        if (filtros.containsKey("dimensionesMax")) {
            query.append(" AND dimensiones_m2 <= :dimensionesMax");
            params.put("dimensionesMax", filtros.get("dimensionesMax"));
        }
        if (filtros.containsKey("banos")) {
            query.append(" AND banos = :banos");
            params.put("banos", filtros.get("banos"));
        }
        if (filtros.containsKey("habitaciones")) {
            query.append(" AND habitacionesTotales = :habitaciones");
            params.put("habitaciones", filtros.get("habitaciones"));
        }
        if (filtros.containsKey("estacionamientos")) {
            query.append(" AND estacionamientos = :estacionamientos");
            params.put("estacionamientos", filtros.get("estacionamientos"));
        }
        if (filtros.containsKey("alcaldia")) {
            query.append(" AND alcaldia = :alcaldia");
            params.put("alcaldia", filtros.get("alcaldia"));
        }

        return find(query.toString(), params)
                .page(pagina - 1, limite)
                .list()
                .stream()
                .map(DepartamentoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Long countWithFilters(Map<String, Object> filtros) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder("1=1");

        if (filtros.containsKey("precioMin")) {
            query.append(" AND precio >= :precioMin");
            params.put("precioMin", filtros.get("precioMin"));
        }
        if (filtros.containsKey("precioMax")) {
            query.append(" AND precio <= :precioMax");
            params.put("precioMax", filtros.get("precioMax"));
        }
        if (filtros.containsKey("dimensionesMin")) {
            query.append(" AND dimensionesM2 >= :dimensionesMin");
            params.put("dimensionesMin", filtros.get("dimensionesMin"));
        }
        if (filtros.containsKey("dimensionesMax")) {
            query.append(" AND dimensionesM2 <= :dimensionesMax");
            params.put("dimensionesMax", filtros.get("dimensionesMax"));
        }
        if (filtros.containsKey("banos")) {
            query.append(" AND banos = :banos");
            params.put("banos", filtros.get("banos"));
        }
        if (filtros.containsKey("habitaciones")) {
            query.append(" AND habitacionesTotales = :habitaciones");
            params.put("habitaciones", filtros.get("habitaciones"));
        }
        if (filtros.containsKey("estacionamientos")) {
            query.append(" AND estacionamientos = :estacionamientos");
            params.put("estacionamientos", filtros.get("estacionamientos"));
        }
        if (filtros.containsKey("alcaldia")) {
            query.append(" AND alcaldia = :alcaldia");
            params.put("alcaldia", filtros.get("alcaldia"));
        }

        return count(query.toString(), params);
    }



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

    @Override
    public List<Departamento> obtenerTodosDepartamentos() {
        List<DepartamentoEntity> departamentoEntities = findAll().list();
        List<Departamento> departamentos = new ArrayList<>();
        for (DepartamentoEntity departamentoEntity : departamentoEntities) {
            departamentos.add(DepartamentoMapper.toDomain(departamentoEntity));
        }
        return departamentos;
    }
    @Override
    public List<Departamento>obtenerMenorAlPromedioDepartamentos(){
        double precioPromedio = obtenerPromedioTodosDepartamentos();

        return findAll()
                .stream()
                .filter(departamento -> departamento.getPrecio() < precioPromedio)
                .map(DepartamentoMapper::toDomain)
                .limit(10)
                .toList();
    }
}
