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
        System.out.println("direccion: " + informe.getDireccion());
        System.out.println("colonia: " + informe.getColonia());
        System.out.println("alcaldia: " + informe.getAlcaldia());
        System.out.println("codigo_postal: " + informe.getCodigoPostal());
        System.out.println("tipo_propiedad: " + informe.getTipoPropiedad());
        System.out.println("recamaras: " + informe.getRecamaras());
        System.out.println("banos: " + informe.getBanos());
        System.out.println("estacionamientos: " + informe.getEstacionamientos());
        System.out.println("dimensiones_m2: " + informe.getDimensionesM2());
        System.out.println("antiguedad_anos: " + informe.getAntiguedadAnos());
        System.out.println("condiciones_propiedad: " + informe.getCondicionesPropiedad());
        System.out.println("anotaciones_extra: " + informe.getAnotacionesExtra());
        System.out.println("valor_estimado: " + informe.getValorEstimado());
        System.out.println("anotaciones_valuacion: " + informe.getAnotacionesValuacion());


        return informe;

    }

}
