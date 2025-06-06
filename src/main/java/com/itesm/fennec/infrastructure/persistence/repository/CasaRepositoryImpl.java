package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.infrastructure.persistence.entity.CasaEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.CasaMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.*;
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

    @Override
    @Transactional
    public Double obtenerPrecioM2() {
        List<Double> precios = findAll()
                .stream()
                .map(CasaEntity::getPrecio_por_m2)
                .filter(Objects::nonNull)
                .toList();

        if (precios.isEmpty()) {
            return 0.0;
        }

        double sum = precios.stream().mapToDouble(Double::doubleValue).sum();
        return sum / precios.size();
    }

    @Override
    public List<Casa> obtenerTodasCasas() {
        List<CasaEntity> casasEntities = findAll().list();
        List<Casa> casas = new ArrayList<>();
        for (CasaEntity casaEntity : casasEntities) {
            casas.add(CasaMapper.toDomain(casaEntity));
        }
        return casas;
    }

    @Override
    public List<Casa> obtenerMenorAlPromedioCasas() {
        double precioPromedio = obtenerPromedioTodasCasas();

        return findAll()
                .stream()
                .filter(casa -> casa.getPrecio() < precioPromedio)
                .map(CasaMapper::toDomain)
                .limit(10)
                .toList();
    }

    @Override
    public List<Casa> findWithFilters(Map<String, Object> filtros, int pagina, int limite) {
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
            query.append(" AND dimensiones_m2 >= :dimensionesMin");
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
            query.append(" AND recamaras = :habitaciones");
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

        System.out.println("=========== FILTROS ENVIADOS DESDE CLIENTE ===========");
        System.out.println("Query final: " + query);
        System.out.println("Parámetros:");
        params.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        System.out.println("Página: " + pagina + " | Límite: " + limite);

        List<CasaEntity> entities = find(query.toString(), params)
                .page(pagina - 1, limite)
                .list();

        System.out.println("Número de entidades encontradas: " + entities.size());

        List<Casa> casas = entities.stream()
                .map(CasaMapper::toDomain)
                .collect(Collectors.toList());

        System.out.println("Número de casas mapeadas: " + casas.size());

        return casas;
    }
    @Override
    public Long obtenerNumeroCasas(){
        return findAll().count();
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
            query.append(" AND dimensiones_m2 >= :dimensionesMin");
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
            query.append(" AND recamaras = :habitaciones");
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
}