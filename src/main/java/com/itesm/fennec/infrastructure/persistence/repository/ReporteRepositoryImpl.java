package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.repository.ReporteRepository;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.ReporteMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ReporteRepositoryImpl implements ReporteRepository, PanacheRepository<ReporteEntity> {

    @Override
    @Transactional
    public InformeValuacion generarReporte(InformeValuacion informe) {
        persist(ReporteMapper.toEntity(informe));
        flush();
        return informe;
    }

}
