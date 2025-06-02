package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InformeValuacionRepository;
import com.itesm.fennec.infrastructure.persistence.entity.InvestmentEntity;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.InformeValuacionMapper;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<InformeValuacion> obtenerInformes(String id){

        System.out.println("userId = " + id);
        List<ReporteEntity> informeEntities = find("idUsuario = ?1", id).list();
        System.out.println("investmentEntities = " + informeEntities);


        List<InformeValuacion> informes = new ArrayList<>();
        for (ReporteEntity reporteEntity : informeEntities) {

            informes.add(InformeValuacionMapper.toDomain(reporteEntity));
        }
        return informes;
    }
}