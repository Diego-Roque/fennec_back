package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.InformeValuacion;

import java.util.List;

public interface InformeValuacionRepository {
    public InformeValuacion insertarInforme(InformeValuacion informe);
    public List<InformeValuacion> obtenerInformes(String id);

}
