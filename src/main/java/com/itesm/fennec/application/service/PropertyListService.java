package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.PropertyList;
import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.domain.repository.DepartamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PropertyListService {

    @Inject
    CasaRepository casaRepository;

    @Inject
    DepartamentoRepository departamentoRepository;

    public PropertyList filtrarPropiedades(String tipoPropiedad, Map<String, Object> filtros, int pagina, int limite) {
        if (tipoPropiedad.equalsIgnoreCase("casa")) {
            List<Casa> casas = casaRepository.findWithFilters(filtros, pagina, limite);
            long total = casaRepository.countWithFilters(filtros);
            int totalPaginas = (int) Math.ceil((double) total / limite);
            return new PropertyList("casa", casas, pagina, totalPaginas, total);
        }

        if (tipoPropiedad.equalsIgnoreCase("departamento")) {
            List<Departamento> departamentos = departamentoRepository.findWithFilters(filtros, pagina, limite);
            long total = departamentoRepository.countWithFilters(filtros);
            int totalPaginas = (int) Math.ceil((double) total / limite);
            return new PropertyList("departamento", departamentos, pagina, totalPaginas, total);
        }

        throw new IllegalArgumentException("Tipo de propiedad no válido");
    }
}
