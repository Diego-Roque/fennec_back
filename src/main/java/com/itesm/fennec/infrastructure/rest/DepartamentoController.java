package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.application.useCase.ObtenerMenorAlPromedioDepartamentoUseCase;
import com.itesm.fennec.application.useCase.ObtenerTodosDepartamentosUseCase;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("api/departamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartamentoController {

    @Inject
    DepartamentoService service;

    @POST
    @Path("/promedio")
    public Response obtenerPromedio(AlcaldiaRequest request) {
        String alcaldia = request.getAlcaldia();

        if (alcaldia == null || alcaldia.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El campo 'alcaldia' es requerido.")
                    .build();
        }

        DepartamentoPrecioPromedioResult result = service.obtenerPromedio(alcaldia);
        return Response.ok(result).build();
    }

    @POST
    @Path("/cantidad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response contarPorAlcaldia(AlcaldiaRequest request) {
        Long cantidad = service.contarPorAlcaldia(request.getAlcaldia());
        return Response.ok(cantidad).build();
    }

    @POST
    @Path("/m2_promedio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPromedioM2(AlcaldiaRequest request) {
        double m2 = service.obtenerPromedioM2(request.getAlcaldia());
        return Response.ok(m2).build();
    }

    @POST
    @Path("/promedio_todos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioTodosDepartamentos() {
        try {
            Double promedio = service.PromedioTodosDepartamentos();
            return Response.ok(promedio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }

    @Inject
    ObtenerTodosDepartamentosUseCase obtenerTodosDepartamentosUseCase;

    @GET
    @Path("/promedio-por-alcaldia")
    public Response obtenerPromedioPorAlcaldia() {
        try {
            List<Departamento> departamentos = obtenerTodosDepartamentosUseCase.execute();
            Map<String, BigDecimal> promediosPorAlcaldia = departamentos.stream()
                    .collect(Collectors.groupingBy(
                            Departamento::getAlcaldia,
                            Collectors.collectingAndThen(
                                    Collectors.averagingDouble(departamento -> departamento.getPrecio().doubleValue()),
                                    promedio -> BigDecimal.valueOf(promedio).setScale(2, RoundingMode.HALF_UP)
                            )
                    ));
            return Response.ok(promediosPorAlcaldia).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener promedios por alcaldía: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/m2_todos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPrecioM2() {
        try {
            Double precio = service.obtenerPrecioM2UseCase();
            return Response.ok(precio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }
    @Inject
    ObtenerTodosDepartamentosUseCase obtenerDepartamentos;
    @GET
    @Path("list-departamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosDepartamentos() {
        try {
            List<Departamento> departamentos = obtenerDepartamentos.execute();
            return Response.ok(departamentos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de casas: " + e.getMessage())
                    .build();
        }
    }



    @Inject
    ObtenerMenorAlPromedioDepartamentoUseCase obtenerMenorAlPromedioDepartamentoUseCase;

    @GET
    @Path("/oportunidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMenorAlPromedio() {
        try {
            List<Departamento> oportunidades = obtenerMenorAlPromedioDepartamentoUseCase.execute();
            if (oportunidades == null || oportunidades.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontraron departamentos por debajo del promedio").build();
            }
            return Response.ok(oportunidades).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener oportunidades").build();
        }
    }
}
