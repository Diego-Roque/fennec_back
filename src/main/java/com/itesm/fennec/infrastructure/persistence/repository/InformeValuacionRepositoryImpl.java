package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.repository.InformeValuacionRepository;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.InformeValuacionMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InformeValuacionRepositoryImpl implements InformeValuacionRepository, PanacheRepositoryBase<ReporteEntity, Integer> {

    @Inject
    InformeValuacionMapper mapper;

    @Override
    @Transactional
    public InformeValuacion insertarInforme(InformeValuacion informeValuacion) {
        persist(mapper.toEntity(informeValuacion));
        flush();
        return informeValuacion;
    }
}